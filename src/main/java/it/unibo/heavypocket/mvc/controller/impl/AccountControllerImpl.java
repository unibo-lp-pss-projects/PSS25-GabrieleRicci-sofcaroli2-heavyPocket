package it.unibo.heavypocket.mvc.controller.impl;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.Comparator;
import java.util.List;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

import it.unibo.heavypocket.mvc.DTO.FiltersDTO;
import it.unibo.heavypocket.mvc.DTO.TransactionDTO;
import it.unibo.heavypocket.mvc.model.Account;
import it.unibo.heavypocket.mvc.model.Statistics;
import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.controller.AccountController;
import it.unibo.heavypocket.mvc.view.AccountView;
import it.unibo.heavypocket.mvc.controller.StatisticsController;
import it.unibo.heavypocket.mvc.model.TransactionType;
import it.unibo.heavypocket.mvc.model.impl.StatisticsImpl;

public final class AccountControllerImpl implements AccountController, StatisticsController {

    private static final String ERROR_CRUD = "Transaction not found";
    private static final String ERROR_AMOUNT = "Amount must be greater than zero";
    private static final String ERROR_FIELDS = "Please fill in all fields";
    private static final String ERROR_AMOUNT_FORMAT = "Invalid amount format";

    private final Account model;
    private final AccountView view;
    private final Statistics statistics;

    public AccountControllerImpl(final Account model, final AccountView view, final Statistics statistics) {
        this.model = model;
        this.view = view;
        this.statistics = statistics;
        this.view.setController(this);
        showTransactions();
        showTags();
        showTotalBalance();
        setAverageValue();
        setPieChartData();
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
    public void showTotalBalance() {
        final String balance = model.getTotalBalance().toString(); // cast a stringa del balance
        view.showBalance(balance);
    }

    // @TODO il balance può andare in negativo?
    // @TODO c'è una lunghezza massima nella descrizione?
    // @TODO c'è un valore massimo per l'importo?
    @Override
    public void addTransaction(final TransactionDTO transactionDTO) {
        try {
            validateTransactionDTO(transactionDTO);
            final Transaction transaction = Transaction.builder()
                    .withId(UUID.randomUUID())
                    .withAmount(validateAmount(transactionDTO.amount()))
                    .withDate(transactionDTO.date())
                    .withDescription(transactionDTO.description())
                    .withType(transactionDTO.type())
                    .withTag(transactionDTO.tag())
                    .build();
            model.addTransaction(transaction);
            showTransactions();
        } catch (final IllegalArgumentException | NullPointerException e) {
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

    // @TODO
    @Override
    public void editTransaction(final UUID id, final TransactionDTO transactionDTO) {
        try {
            validateTransactionDTO(transactionDTO);
            final Transaction transaction = Transaction.builder()
                    .withId(id)
                    .withAmount(validateAmount(transactionDTO.amount()))
                    .withDate(transactionDTO.date())
                    .withDescription(transactionDTO.description())
                    .withType(transactionDTO.type())
                    .withTag(transactionDTO.tag())
                    .build();
            model.editTransaction(id, transaction);
            showTransactions();
        } catch (final IllegalArgumentException | NullPointerException e) {
            view.showError(e.getMessage());
        }
    }

    @Override
    public void deleteTransaction(final UUID id) {
        model.getTransactionById(id).ifPresentOrElse(
                model::deleteTransaction,
                () -> view.showError(ERROR_CRUD));
        showTransactions();
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

    // @TODO capire se spostare la validazione in una classe apposita
    private void validateTransactionDTO(final TransactionDTO transactionDTO) {
        if (transactionDTO == null
                || transactionDTO.type() == null
                || transactionDTO.amount() == null
                || transactionDTO.amount().isBlank()
                || transactionDTO.date() == null
                || transactionDTO.description() == null
                || transactionDTO.description().isBlank()
                || transactionDTO.tag() == null) {
            throw new IllegalArgumentException(ERROR_FIELDS);
        }
    }

    // @TODO capire se spostare la validazione in una classe apposita
    private BigDecimal validateAmount(final String amountString) {
        final String amount = amountString.trim().replace(',', '.');
        final BigDecimal finalAmount;
        try {
            finalAmount = new BigDecimal(amount);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_AMOUNT_FORMAT);
        }
        if (finalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(ERROR_AMOUNT);
        }
        return finalAmount;
    }

    @Override
    public void setAverageValue() {
        final LocalDate todayDate = LocalDate.now(); // data di oggi per sapere il mese corrente
        final List<Transaction> transactionsFiltered = model.getTransactions().stream()
                .filter(t -> t.getDate().getMonth() == todayDate.getMonth()
                        && t.getDate().getYear() == todayDate.getYear())
                .toList();
        final List<Transaction> expenses = statistics.getExpenses(transactionsFiltered);
        final List<Transaction> incomes = statistics.getIncomes(transactionsFiltered);
        final String avarageExpense = statistics.getAverage(expenses).toString();
        final String avarageIncome = statistics.getAverage(incomes).toString();
        this.view.showAverage(avarageExpense, avarageIncome);
    }

    @Override
    public void setPieChartData() {
        final List<Transaction> expenses = statistics.getExpenses(model.getTransactions());
        final Map<Tag, BigDecimal> expenseByTag = this.statistics.getExpenseByTag(expenses);
        this.view.showPieChartData(expenseByTag);           
    }

    // @Override
    // public Map<LocalDate, BigDecimal> getLineChartData() {
    // return statistics.getStatisticsByMonth(this.transactions);
    // }
}
