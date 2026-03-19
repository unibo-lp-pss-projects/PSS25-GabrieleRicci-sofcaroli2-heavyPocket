package it.unibo.heavypocket.mvc.model.impl;

import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDate;

import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.TransactionType;
import it.unibo.heavypocket.mvc.model.TransactionBuilder;
import it.unibo.heavypocket.mvc.model.Tag;

/**
 * Implementation of the TransactionBuilder interface. It provides methods to
 * set the properties of a Transaction and a build method to instance a
 * Transaction.
 */
public final class TransactionBuilderImpl implements TransactionBuilder {

    private UUID id;
    private BigDecimal amount;
    private LocalDate date;
    private String description;
    private TransactionType type;
    private Tag tag;

    @Override
    public TransactionBuilder withId(final UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public TransactionBuilder withAmount(final BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public TransactionBuilder withDate(final LocalDate date) {
        this.date = date;
        return this;
    }

    @Override
    public TransactionBuilder withDescription(final String description) {
        this.description = description;
        return this;
    }

    @Override
    public TransactionBuilder withType(final TransactionType type) {
        this.type = type;
        return this;
    }

    @Override
    public TransactionBuilder withTag(final Tag tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public Transaction build() {
        return new TransactionImpl(
                id,
                amount,
                date,
                description,
                type,
                tag);
    }
}
