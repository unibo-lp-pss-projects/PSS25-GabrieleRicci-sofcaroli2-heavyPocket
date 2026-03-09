package it.unibo.heavypocket.mvc.model.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import static java.util.Objects.requireNonNull;

import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.Tag;

public final class TransactionImpl implements Transaction {

    private static final String ID_ERROR_MESSAGE = "ID cannot be null";
    private static final String NULL_AMOUNT_ERROR_MESSAGE = "Amount cannot be null";
    private static final String NEGATIVE_AMOUNT_ERROR_MESSAGE = "Amount must be positive and non-zero";
    private static final String DATE_ERROR_MESSAGE = "Date cannot be null";
    private static final String DESCRIPTION_ERROR_MESSAGE = "Description cannot be null or blank";
    private static final String TAG_ERROR_MESSAGE = "Tag cannot be null";

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
        this.id = requireNonNull(id, ID_ERROR_MESSAGE);
        requireNonNull(amount, NULL_AMOUNT_ERROR_MESSAGE);
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(NEGATIVE_AMOUNT_ERROR_MESSAGE);
        }
        this.amount = amount;
        this.date = requireNonNull(date, DATE_ERROR_MESSAGE);
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException(DESCRIPTION_ERROR_MESSAGE);
        }
        this.description = description;
        this.expense = expense;
        this.tag = requireNonNull(tag, TAG_ERROR_MESSAGE);
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
