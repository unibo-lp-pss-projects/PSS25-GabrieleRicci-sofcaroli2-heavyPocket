package it.unibo.heavypocket.mvc.model.impl;

import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.Account;

public class AccountImpl implements Account {

    private final List<Transaction> transactions;

    public AccountImpl(final List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public List<Transaction> getTransactions() {
        return List.copyOf(this.transactions);
    }

    @Override
    public BigDecimal getTotalBalance() {
        return transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    // // @TODO
    // @Override
    // public void addTransaction(final Transaction transaction) {
    // return;
    // }

    // // @TODO
    // @Override
    // public void editTransaction(final Transaction transaction) {
    // return;
    // }

    // // @TODO
    // @Override
    // public void deleteTransaction(final Transaction transaction) {
    // return;
    // }

    @Override
    public List<Transaction> searchByType(final boolean expense) {
        return transactions.stream()
                .filter(t -> t.isExpense() == expense)
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
}
