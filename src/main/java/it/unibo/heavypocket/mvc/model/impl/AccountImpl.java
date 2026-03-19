package it.unibo.heavypocket.mvc.model.impl;

import java.util.List;
import java.util.Set;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.util.Objects;

import it.unibo.heavypocket.mvc.model.Account;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.TransactionType;
import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Budget;
import it.unibo.heavypocket.mvc.model.Statistics;

public final class AccountImpl implements Account {

    private static final String ERROR_CRUD = "Transaction not found";

    private final List<Transaction> transactions;
    private final Set<Tag> tags;
    private final Budget budget;
    private final Statistics statistics;

    public AccountImpl(
            final List<Transaction> transactions,
            final Set<Tag> tags,
            final Budget budget,
            final Statistics statistics) {
        this.transactions = new ArrayList<>(Objects.requireNonNull(transactions));
        this.tags = Set.copyOf(Objects.requireNonNull(tags));
        this.budget = Objects.requireNonNull(budget);
        this.statistics = Objects.requireNonNull(statistics);
    }

    @Override
    public BigDecimal getTotalBalance() {
        return transactions.stream()
                .map(Transaction::getSignedAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<Transaction> getTransactions() {
        return List.copyOf(transactions);
    }

    @Override
    public Set<Tag> getTags() {
        return Set.copyOf(tags);
    }

    @Override
    public Budget getBudget() {
        return budget;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    @Override
    public Optional<Transaction> getTransactionById(final UUID id) {
        return transactions.stream()
                .filter(t -> t.getId().equals(id))
                .findAny();
    }

    @Override
    public List<Transaction> searchByType(final TransactionType type) {
        return transactions.stream()
                .filter(t -> type.matches(t))
                .toList();
    }

    @Override
    public List<Transaction> searchByDate(final LocalDate date) {
        return transactions.stream()
                .filter(t -> t.getDate().equals(date))
                .toList();
    }

    @Override
    public List<Transaction> searchByTag(final Tag tag) {
        return transactions.stream()
                .filter(t -> t.getTag().equals(tag))
                .toList();
    }

    @Override
    public void addTransaction(final Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public void editTransaction(final UUID id, final Transaction newTransaction) {
        final int index = IntStream.range(0, transactions.size())
                .filter(i -> transactions.get(i).getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_CRUD));
        transactions.set(index, newTransaction);
    }

    @Override
    public void deleteTransaction(final Transaction transaction) {
        transactions.remove(transaction);
    }
}
