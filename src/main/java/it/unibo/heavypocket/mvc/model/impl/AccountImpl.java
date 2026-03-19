package it.unibo.heavypocket.mvc.model.impl;

import java.util.List;
import java.util.Set;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import java.util.Optional;
import java.util.stream.IntStream;

import it.unibo.heavypocket.mvc.model.Account;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.TransactionType;
import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Budget;
import it.unibo.heavypocket.mvc.model.Statistics;

/**
 * Implementation of the Account's interface.
 */
public final class AccountImpl implements Account {

    private static final String ERROR_CRUD = "Transaction not found";

    private List<Transaction> transactions;
    private Set<Tag> tags;
    private Budget budget;
    private Statistics statistics;

    /**
     * Constructor of the class StatisticsImpl.
     * 
     * @param balance total balance
     * @param transactions list of transactions
     * @param tags set of tags
     * @param budget interface for budget
     * @param statistics interface for statistics
     */
    public AccountImpl(
            final List<Transaction> transactions,
            final Set<Tag> tags,
            final Budget budget,
            final Statistics statistics) {
        this.transactions = transactions;
        this.tags = tags;
        this.budget = budget;
        this.statistics = statistics;
    }

    @Override
    public BigDecimal getTotalBalance() {
        return transactions.stream()
            .map(Transaction::getSignedAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public List<Transaction> getTransactions() {
        return List.copyOf(this.transactions);
    }

    @Override
    public Set<Tag> getTags() {
        return Set.copyOf(this.tags);
    }

    @Override
    public Budget getBudget() {
        return this.budget;
    }

    @Override
    public Statistics getStatistics() {
        return this.statistics;
    }

    @Override
    public Optional<Transaction> getTransactionById(final UUID id) {
        return this.transactions.stream()
            .filter(t -> t.getId().equals(id))
            .findAny();
    }

    @Override
    public List<Transaction> searchByType(final TransactionType type) {
        return this.transactions.stream()
            .filter(t -> type.matches(t))
            .toList();
    }

    @Override
    public List<Transaction> searchByDate(final LocalDate date) {
        return this.transactions.stream()
            .filter(t -> t.getDate().equals(date))
            .toList();
    }

    @Override
    public List<Transaction> searchByTag(final Tag tag) {
        return this.transactions.stream()
            .filter(t -> t.getTag().equals(tag))
            .toList();
    }

    @Override
    public void addTransaction(final Transaction transaction) {
        this.transactions.add(transaction);
    }

    @Override
    public void editTransaction(final UUID id, final Transaction newTransaction) {
        final int index = IntStream.range(0, transactions.size())
            .filter(i -> transactions.get(i).getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(ERROR_CRUD));
        this.transactions.set(index, newTransaction);
    }

    @Override
    public void deleteTransaction(final Transaction transaction) {
        this.transactions.remove(transaction);
    }
}
