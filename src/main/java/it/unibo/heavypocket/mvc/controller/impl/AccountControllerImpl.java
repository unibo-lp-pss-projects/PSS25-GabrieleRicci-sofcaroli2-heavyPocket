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
import it.unibo.heavypocket.mvc.controller.AccountController;
import it.unibo.heavypocket.mvc.controller.validation.Validation;
import it.unibo.heavypocket.mvc.view.AccountView;
import it.unibo.heavypocket.mvc.dto.FiltersDTO;
import it.unibo.heavypocket.mvc.dto.TransactionDTO;
import it.unibo.heavypocket.persistence.Saver;

/**
 * Implementation of the AccountController interface.
 */
public final class AccountControllerImpl implements AccountController {

    private static final String ERROR_CRUD = "Transaction not found";
    private static final String ERROR_PERSISTENCE = "Error saving data";
    private static final String ERROR_BUDGET_LIMIT = "Budget limit cannot be empty";

    private final Account model;
    private final AccountView view;
    private final Saver saver;

    /**
     * Constructor for AccountControllerImpl. It initializes the model, the view and
     * the saver, and sets the controller for the view. It also updates the view
     * with the current state of the model and shows the tags.
     * 
     * @param model the model of the app.
     * @param view  the view of the app.
     * @param saver service to save data into json.
     */
    public AccountControllerImpl(
            final Account model,
            final AccountView view,
            final Saver saver) {
        this.model = model;
        this.view = view;
        this.saver = saver;
        view.setController(this);
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
                            transaction.getType(),
                            String.valueOf(transaction.getAmount()),
                            transaction.getDate(),
                            transaction.getDescription(),
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

    @Override
    public void showBudgetElements() {
        final BigDecimal budgetLimit = model.getBudget().getLimit();
        final BigDecimal currentSpent = calculateMonthlyExpenses();
        view.showBudgetElements(budgetLimit.toString(), currentSpent.toString());
    }

    @Override
    public void updateBudgetLimit(final String newLimit) {
        if (newLimit == null || newLimit.isBlank()) {
            view.showError(ERROR_BUDGET_LIMIT);
            return;
        }
        try {
            final BigDecimal newLimitValue = Validation.validateAmount(newLimit);
            model.getBudget().setLimit(newLimitValue);
            view.showBudgetElements(newLimitValue.toString(), calculateMonthlyExpenses().toString());
            isBudgetExceeded();
            persistState();
        } catch (final IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    @Override
    public void isBudgetExceeded() {
        final BigDecimal budgetLimit = model.getBudget().getLimit();
        final BigDecimal currentSpent = calculateMonthlyExpenses();
        final boolean isExceeded = currentSpent.compareTo(budgetLimit) > 0;
        view.showLimitExceeded(isExceeded);
    }

    @Override
    public void setAverageValue() {
        final List<Transaction> transactionsOfMonth = getTransactionsByCurrentMonth();
        final List<Transaction> expenses = model.getStatistics().getExpenses(transactionsOfMonth);
        final List<Transaction> incomes = model.getStatistics().getIncomes(transactionsOfMonth);
        final String averageExpense = model.getStatistics().getAverage(expenses).toString();
        final String averageIncome = model.getStatistics().getAverage(incomes).toString();
        view.showAverage(averageExpense, averageIncome);
    }

    @Override
    public void setPieChartData() {
        final List<Transaction> transactions = model.getTransactions();
        final List<Transaction> expenses = model.getStatistics().getExpenses(transactions);
        final List<Transaction> incomes = model.getStatistics().getIncomes(transactions);
        final Map<Tag, BigDecimal> expenseByTag = model.getStatistics().getAmountByTag(expenses);
        final Map<Tag, BigDecimal> incomesByTag = model.getStatistics().getAmountByTag(incomes);
        view.showPieChartData(expenseByTag, incomesByTag);
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
        final List<Transaction> expenses = model.getStatistics().getExpenses(getTransactionsByCurrentMonth());
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
