package it.unibo.heavypocket.mvc.model.impl;

import java.util.List;
import java.util.Set;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import java.util.Optional;

import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.Account;
import it.unibo.heavypocket.mvc.model.TransactionType;

//@TODO controllare le eccezioni se sono da lanciare o no, se si quali
public final class AccountImpl implements Account {

    private List<Transaction> transactions;
    private BigDecimal balance;
    private BigDecimal budget;
    private BigDecimal savingTarget;
    private Set<Tag> tags;

    public AccountImpl(
            final List<Transaction> transactions,
            final BigDecimal balance,
            final BigDecimal budget,
            final BigDecimal savingTarget,
            final Set<Tag> tags) {
        this.transactions = transactions;
        this.balance = balance;
        this.budget = budget;
        this.savingTarget = savingTarget;
        this.tags = tags;
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
    public BigDecimal getTotalBalance() {
        return this.transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public BigDecimal getBudgetLimit() {
        return this.budget;
    }

    @Override
    public void addTransaction(final Transaction transaction) {
        this.transactions.add(transaction);
    }

    @Override
    public void editTransaction(final UUID id, final Transaction newTransaction) {
        this.transactions = this.transactions.stream()
                .map(t -> t.getId().equals(id) ? newTransaction : t)
                .toList();
    }

    @Override
    public void deleteTransaction(final Transaction transaction) {
        this.transactions.remove(transaction);
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
    public Optional<Transaction> getTransactionById(final UUID id) {
        return this.transactions.stream()
                .filter(t -> t.getId().equals(id))
                .findAny();
    }
}
