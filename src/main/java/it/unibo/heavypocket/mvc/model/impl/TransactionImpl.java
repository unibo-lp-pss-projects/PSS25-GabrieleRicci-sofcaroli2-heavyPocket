package it.unibo.heavypocket.mvc.model.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.Tag;

public class TransactionImpl implements Transaction {

    private static final String AMOUNT_ERROR_MESSAGE = "Amount must be positive and non-zero";

    private final UUID id;
    private final BigDecimal amount;
    private final LocalDate date;
    private final String description;
    private final boolean expense;
    private final Tag tag;

    public TransactionImpl(
            final UUID id,
            final BigDecimal amount,
            final LocalDate date,
            final String description,
            final boolean expense,
            final Tag tag) {
        // @TODO: validazione dei parametri
        this.id = id;
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(AMOUNT_ERROR_MESSAGE);
        }
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.expense = expense;
        this.tag = tag;
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public BigDecimal getAmount() {
        return this.amount;
    }

    @Override
    public BigDecimal getSignedAmount() {
        return this.expense ? this.amount.negate() : this.amount;
    }

    @Override
    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public boolean isExpense() {
        return this.expense;
    }

    @Override
    public Tag getTag() {
        return this.tag;
    }
}
