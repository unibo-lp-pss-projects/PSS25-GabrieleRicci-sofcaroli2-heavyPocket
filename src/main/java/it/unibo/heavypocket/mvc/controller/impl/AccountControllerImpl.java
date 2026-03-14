package it.unibo.heavypocket.mvc.controller.impl;

import java.math.BigDecimal;
import java.nio.file.DirectoryStream.Filter;
import java.time.LocalDate;
import java.util.UUID;
import java.util.Comparator;
import java.util.List;

import it.unibo.heavypocket.mvc.model.Account;
import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.FiltersData;
import it.unibo.heavypocket.mvc.model.TransactionType;
import it.unibo.heavypocket.mvc.controller.AccountController;
import it.unibo.heavypocket.mvc.view.AccountView;

// @TODO ordinamento transazioni
public final class AccountControllerImpl implements AccountController {

    private static final String ERROR_FILTERS = "No transactions found";

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
        // final Transaction transaction = Transaction.builder()
        // .withId(UUID.randomUUID())
        // .withAmount(amount)
        // .withDate(date)
        // .withDescription(description)
        // .withType(expense ? TransactionType.EXPENSE : TransactionType.INCOME)
        // .withTag(tag)
        // .build();
        // model.addTransaction(transaction);
        return;
    }

    @Override
    public void editTransaction(
            final UUID id,
            final BigDecimal amount,
            final LocalDate date,
            final String description,
            final boolean expense,
            final Tag tag) {
        // final Transaction newTransaction = Transaction.builder()
        // .withId(id)
        // .withAmount(amount)
        // .withDate(date)
        // .withDescription(description)
        // .isExpense(expense)
        // .withTag(tag)
        // .build();
        // model.editTransaction(id, newTransaction);
        return;
    }

    @Override
    public void deleteTransaction(final Transaction transaction) {
        // model.deleteTransaction(transaction);
        return;
    }

    @Override
    public void search(FiltersData filters) {
        if (filters == null) {
            showTransactions();
            return;
        }
        List<Transaction> filteredTransactions = model.getTransactions().stream()
                .filter(t -> filterByType(t, filters.type()))
                .filter(t -> filterByDate(t, filters.date()))
                .filter(t -> filterByTag(t, filters.tag()))
                .toList();
        if (filteredTransactions.isEmpty()) {
            view.showError(ERROR_FILTERS);
        } else {
            view.showTransactionList(filteredTransactions);
        }
    }

    private boolean filterByType(final Transaction t, final TransactionType type) {
        if (type == null)
            return true;
        return t.getType() == type;
    }

    private boolean filterByDate(final Transaction t, final LocalDate date) {
        if (date == null)
            return true;
        return date.equals(t.getDate());
    }

    private boolean filterByTag(final Transaction t, final Tag tag) {
        if (tag == null)
            return true;
        return t.getTag().equals(tag);
    }
}
