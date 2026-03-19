package it.unibo.heavypocket.mvc.model.impl;

import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDate;
import static java.util.Objects.requireNonNull;

import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.TransactionType;
import it.unibo.heavypocket.mvc.model.Tag;

/**
 * Implementation of the Transaction interface. It includes amount, date,
 * description, type, and tag.
 */
public final class TransactionImpl implements Transaction {

    private static final String ID_ERROR_MESSAGE = "ID cannot be null";
    private static final String NULL_AMOUNT_ERROR_MESSAGE = "Amount cannot be null";
    private static final String NEGATIVE_AMOUNT_ERROR_MESSAGE = "Amount must be positive and non-zero";
    private static final String DATE_ERROR_MESSAGE = "Date cannot be null";
    private static final String FUTURE_DATE_ERROR_MESSAGE = "Date cannot be in the future";
    private static final String DESCRIPTION_ERROR_MESSAGE = "Description cannot be null or blank";
    private static final String TYPE_ERROR_MESSAGE = "Type cannot be null";
    private static final String TAG_ERROR_MESSAGE = "Tag cannot be null";

    private final UUID id;
    private final BigDecimal amount;
    private final LocalDate date;
    private final String description;
    private final TransactionType type;
    private final Tag tag;

    /**
     * Constructor for TransactionImpl.
     *
     * @param id the unique identifier of the transaction.
     * @param amount the amount of the transaction.
     * @param date the date of the transaction.
     * @param description the description of the transaction.
     * @param type the type of the transaction.
     * @param tag the tag of the transaction.
     */
    public TransactionImpl(
            final UUID id,
            final BigDecimal amount,
            final LocalDate date,
            final String description,
            final TransactionType type,
            final Tag tag) {
        this.id = requireNonNull(id, ID_ERROR_MESSAGE);
        this.amount = requireNonNull(amount, NULL_AMOUNT_ERROR_MESSAGE);
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(NEGATIVE_AMOUNT_ERROR_MESSAGE);
        }
        this.date = requireNonNull(date, DATE_ERROR_MESSAGE);
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(FUTURE_DATE_ERROR_MESSAGE);
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException(DESCRIPTION_ERROR_MESSAGE);
        }
        this.description = description;
        this.type = requireNonNull(type, TYPE_ERROR_MESSAGE);
        this.tag = requireNonNull(tag, TAG_ERROR_MESSAGE);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public BigDecimal getSignedAmount() {
        return type == TransactionType.EXPENSE ? amount.negate() : amount;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public TransactionType getType() {
        return type;
    }

    @Override
    public Tag getTag() {
        return tag;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TransactionImpl other = (TransactionImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
