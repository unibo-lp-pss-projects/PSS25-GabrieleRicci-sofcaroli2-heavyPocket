package it.unibo.heavypocket.mvc.controller.impl;

import java.util.UUID;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.io.IOException;

import it.unibo.heavypocket.mvc.model.Account;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Statistics;
import it.unibo.heavypocket.mvc.controller.AccountController;
import it.unibo.heavypocket.mvc.controller.validation.Validation;
import it.unibo.heavypocket.mvc.view.AccountView;
import it.unibo.heavypocket.mvc.DTO.FiltersDTO;
import it.unibo.heavypocket.mvc.DTO.TransactionDTO;
import it.unibo.heavypocket.persistence.Saver;

// @TODO ripulire tutto il controller, cercare ripetizioni nei metodi,
// capire cosa può diventare un campo e cosa ha senso spostare in un file di utilità
public final class AccountControllerImpl implements AccountController {

    private static final String ERROR_CRUD = "Transaction not found";
    private static final String ERROR_PERSISTENCE = "Error saving data";

    private final Account model;
    private final AccountView view;
    private final Statistics statistics;
    private final Saver saver;

    public AccountControllerImpl(
            final Account model,
            final AccountView view,
            final Statistics statistics,
            final Saver saver) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
        this.statistics = statistics;
        this.saver = saver;
        updateView();
        showTags();
    }

    @Override
    public void showTotalBalance() {
        final String balance = model.getTotalBalance().toString();
        view.showBalance(balance);
    }

    @Override
    public void showTransactions() {
        final List<Transaction> transactions = model.getTransactions().stream()
                .sorted(Comparator.comparing(Transaction::getDate).reversed())
                .toList();
        view.showTransactionList(transactions);
    }

    @Override
    public void showTags() {
        final List<Tag> tags = model.getTags().stream().toList();
        view.showTagList(tags);
    }

    @Override
    public void search(final FiltersDTO filters) {
        if (filters == null) {
            showTransactions();
            return;
        }
        final List<Transaction> filteredTransactions = model.getTransactions().stream()
                .filter(t -> filters.type() == null || model.searchByType(filters.type()).contains(t))
                .filter(t -> filters.date() == null || model.searchByDate(filters.date()).contains(t))
                .filter(t -> filters.tag() == null || model.searchByTag(filters.tag()).contains(t))
                .sorted(Comparator.comparing(Transaction::getDate).reversed())
                .toList();
        view.showTransactionList(filteredTransactions);
    }

    // @TODO il balance può andare in negativo?
    // @TODO c'è una lunghezza massima nella descrizione?
    // @TODO c'è un valore massimo per l'importo?
    @Override
    public void addTransaction(final TransactionDTO transactionDTO) {
        try {
            Validation.validateTransactionDTO(transactionDTO);
            final Transaction transaction = getTransactionByDto(UUID.randomUUID(), transactionDTO);
            model.addTransaction(transaction);
            updateView();
            persistState();
        } catch (final IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    @Override
    public void callToEditTransaction(final UUID id) {
        model.getTransactionById(id).ifPresentOrElse(
                transaction -> {
                    final TransactionDTO transactionDTO = new TransactionDTO(
                            String.valueOf(transaction.getAmount()),
                            transaction.getDate(),
                            transaction.getDescription(),
                            transaction.getType(),
                            transaction.getTag());
                    view.showEditTransaction(id, transactionDTO);
                },
                () -> view.showError(ERROR_CRUD));
    }

    @Override
    public void editTransaction(final UUID id, final TransactionDTO transactionDTO) {
        try {
            Validation.validateTransactionDTO(transactionDTO);
            final Transaction transaction = getTransactionByDto(id, transactionDTO);
            model.editTransaction(id, transaction);
            updateView();
            persistState();
        } catch (final IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    @Override
    public void deleteTransaction(final UUID id) {
        model.getTransactionById(id).ifPresentOrElse(
                transaction -> {
                    model.deleteTransaction(transaction);
                    updateView();
                    persistState();
                },
                () -> {
                    view.showError(ERROR_CRUD);
                });
    }

    // @TODO capire dove è meglio impostare un valore di deafault per il budget per
    // evitare che sia null
    @Override
    public void showBudgetElements() {
        final BigDecimal budgetLimit = this.model.getBudget().getLimit();
        // @TODO se il budgetLimit è 0 o negaticvo lanciare un'errore nella view o se è
        // null
        final BigDecimal currentSpent = calculateMonthlyExpenses();
        this.view.showBudgetElements(budgetLimit.toString(), currentSpent.toString());
    }

    @Override
    public void updateBudgetLimit(final String newLimit) {
        // @TODO valudazione dati in input e controllo che possa essre un bigdecimal
        final BigDecimal newLimitValue = Validation.validateAmount(newLimit);
        this.model.getBudget().setLimit(newLimitValue);
        this.view.showBudgetElements(newLimitValue.toString(), calculateMonthlyExpenses().toString());
        isBudgetExceeded();
    }

    @Override
    public void isBudgetExceeded() {
        final BigDecimal budgetLimit = this.model.getBudget().getLimit();
        final BigDecimal currentSpent = calculateMonthlyExpenses();
        final boolean isExceeded = currentSpent.compareTo(budgetLimit) > 0;
        // @TODO se il budgetLimit è 0 o negaticvo lanciare un'errore nella view o se è
        // null
        if (isExceeded) {
            this.view.showLimitExceeded();
        } else {
            this.view.showLimitNotExceeded();
        }
    }

    @Override
    public void setAverageValue() {
        final List<Transaction> transactionsOfMonth = getTransactionsByCurrentMonth();
        final List<Transaction> expenses = statistics.getExpenses(transactionsOfMonth);
        final List<Transaction> incomes = statistics.getIncomes(transactionsOfMonth);
        if (expenses.isEmpty() || incomes.isEmpty()) {
            this.view.showAverage("0.00", "0.00");
            return;
        }
        final String averageExpense = statistics.getAverage(expenses).toString();
        final String averageIncome = statistics.getAverage(incomes).toString();
        this.view.showAverage(averageExpense, averageIncome);
    }

    @Override
    public void setPieChartData() {
        final List<Transaction> transactions = model.getTransactions();
        final List<Transaction> expenses = statistics.getExpenses(transactions);
        final List<Transaction> incomes = statistics.getIncomes(transactions);
        final Map<Tag, BigDecimal> expenseByTag = this.statistics.getAverageByTag(expenses);
        final Map<Tag, BigDecimal> incomesByTag = this.statistics.getAverageByTag(incomes);
        this.view.showPieChartData(expenseByTag, incomesByTag);
    }

    private void updateView() {
        showTransactions();
        showTotalBalance();
        showBudgetElements();
        isBudgetExceeded();
        setAverageValue();
        setPieChartData();
    }

    private Transaction getTransactionByDto(final UUID id, final TransactionDTO transactionDTO) {
        return Transaction.builder()
                .withId(id)
                .withAmount(Validation.validateAmount(transactionDTO.amount()))
                .withDate(transactionDTO.date())
                .withDescription(transactionDTO.description())
                .withType(transactionDTO.type())
                .withTag(transactionDTO.tag())
                .build();
    }

    private BigDecimal calculateMonthlyExpenses() {
        final List<Transaction> expenses = statistics.getExpenses(getTransactionsByCurrentMonth());
        return expenses.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private List<Transaction> getTransactionsByCurrentMonth() {
        final LocalDate today = LocalDate.now();
        return model.getTransactions().stream()
                .filter(t -> t.getDate().getMonth() == today.getMonth()
                        && t.getDate().getYear() == today.getYear())
                .toList();
    }

    private void persistState() {
        try {
            saver.saveAccount(model);
        } catch (final IOException e) {
            view.showError(ERROR_PERSISTENCE);
        }
    }
}
