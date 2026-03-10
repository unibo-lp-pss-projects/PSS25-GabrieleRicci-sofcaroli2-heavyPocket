package it.unibo.heavypocket.mvc.controller.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.controller.AccountController;
import it.unibo.heavypocket.mvc.model.Transaction;

public final class AccountControllerImpl implements AccountController {

    // @TODO
    @Override
    public List<Transaction> getTransactions() {
        return null;
    }

    // @TODO
    @Override
    public BigDecimal getTotalBalance() {
        return null;
    }

    @Override
    public void addTransaction(
            final BigDecimal amount,
            final LocalDate date,
            final String description,
            final boolean expense,
            final Tag tag) {
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
        return;
    }

    @Override
    public void deleteTransaction(final UUID id) {
        return;
    }

    // @TODO
    @Override
    public void searchByType(final boolean expense) {
        return;
    }

    // @TODO
    @Override
    public void searchByDate(final LocalDate date) {
        return;
    }

    // @TODO
    @Override
    public void searchByTag(final Tag tag) {
        return;
    }
}
