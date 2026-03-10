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

    // @TODO NB. ricordare di tornare una copia della lista, MAI l'originale!!
    @Override
    public void getTransactions() {
        return;
    }

    // @TODO
    @Override
    public void getTotalBalance() {
        return;
    }

    // @TODO
    @Override
    public void addTransaction(final Transaction transaction) {
        return;
    }

    // @TODO
    @Override
    public void editTransaction(final Transaction transaction) {
        return;
    }

    // @TODO
    @Override
    public void deleteTransaction(final Transaction transaction) {
        return;
    }

    // @TODO
    @Override
    public void searchByType() {
        return;
    }

    // @TODO
    @Override
    public void searchByDate() {
        return;
    }

    // @TODO
    @Override
    public void searchByTag() {
        return;
    }
}
