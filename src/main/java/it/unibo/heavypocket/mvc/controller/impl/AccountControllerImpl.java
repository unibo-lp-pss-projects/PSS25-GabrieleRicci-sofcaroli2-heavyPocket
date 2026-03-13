package it.unibo.heavypocket.mvc.controller.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

import it.unibo.heavypocket.mvc.model.Account;
import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.controller.AccountController;
import it.unibo.heavypocket.mvc.view.AccountView;

public final class AccountControllerImpl implements AccountController {

    private final Account model;
    private final AccountView view;

    public AccountControllerImpl(final Account model, final AccountView view) {
        this.model = model;
        this.view = view;
        this.view.setController(this);
        showTransactions();
        showTags();
    }

    @Override
    public List<Transaction> getTransactions() {
        return model.getTransactions();
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
    public BigDecimal getTotalBalance() {
        return model.getTotalBalance();
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
}
