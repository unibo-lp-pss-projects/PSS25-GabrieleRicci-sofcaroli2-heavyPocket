package it.unibo.heavypocket.mvc.controller.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

import it.unibo.heavypocket.mvc.model.Account;
import it.unibo.heavypocket.mvc.model.Statistics;
import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.controller.AccountController;
import it.unibo.heavypocket.mvc.view.AccountView;
import it.unibo.heavypocket.mvc.controller.StatisticsController;
import it.unibo.heavypocket.mvc.model.impl.StatisticsImpl;

public final class AccountControllerImpl implements AccountController, StatisticsController {

    private final Account model;
    private final AccountView view;
    private final Statistics statistics = new StatisticsImpl();

    public AccountControllerImpl(final Account model, final AccountView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
        showTransactions();
        showTags();
        showTotalBalance();
        setAverageValue();
    }

    @Override
    public void showTransactions() {
        final List<Transaction> transactions = model.getTransactions();
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

    @Override
    public void addTransaction(
            final BigDecimal amount,
            final LocalDate date,
            final String description,
            final boolean expense,
            final Tag tag) {
        final Transaction transaction = Transaction.builder()
                .withId(UUID.randomUUID())
                .withAmount(amount)
                .withDate(date)
                .withDescription(description)
                .isExpense(expense)
                .withTag(tag)
                .build();
        model.addTransaction(transaction);
    }

    @Override
    public void editTransaction(
            final UUID id,
            final BigDecimal amount,
            final LocalDate date,
            final String description,
            final boolean expense,
            final Tag tag) {
        final Transaction newTransaction = Transaction.builder()
                .withId(id)
                .withAmount(amount)
                .withDate(date)
                .withDescription(description)
                .isExpense(expense)
                .withTag(tag)
                .build();
        model.editTransaction(id, newTransaction);
    }

    @Override
    public void deleteTransaction(final Transaction transaction) {
        model.deleteTransaction(transaction);
    }

    @Override
    public List<Transaction> searchByType(final boolean expense) {
        return model.searchByType(expense);
    }

    @Override
    public List<Transaction> searchByDate(final LocalDate date) {
        return model.searchByDate(date);
    }

    @Override
    public List<Transaction> searchByTag(final Tag tag) {
        return model.searchByTag(tag);
    }

    @Override
    public void setAverageValue() {
        final LocalDate todayDate = LocalDate.now(); // data di oggi per sapere il mese corrente
        final List<Transaction> transactionsFiltered = model.getTransactions().stream()
            .filter(t -> t.getDate().getMonth() == todayDate.getMonth()
                && t.getDate().getYear() == todayDate.getYear())
            .toList();
        final List<Transaction> expenses = transactionsFiltered.stream()
            .filter(Transaction::isExpense)
            .toList();
        final List<Transaction> incomes = transactionsFiltered.stream()
            .filter(t -> !t.isExpense())
            .toList();
        final String avarageExpense = statistics.getAverage(expenses).toString();
        final String avarageIncome = statistics.getAverage(incomes).toString();
        this.view.showAverage(avarageExpense, avarageIncome);
    }

    // @Override
    // public Map<Tag, BigDecimal> getPieChartData() {
    // return statistics.getExpenseByTag(this.expenses);
    // }

    // @Override
    // public Map<LocalDate, BigDecimal> getLineChartData() {
    // return statistics.getStatisticsByMonth(this.transactions);
    // }
}
