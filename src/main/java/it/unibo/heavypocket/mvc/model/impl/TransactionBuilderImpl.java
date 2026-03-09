package it.unibo.heavypocket.mvc.model.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import it.unibo.heavypocket.mvc.model.TransactionBuilder;
import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Transaction;

public class TransactionBuilderImpl implements TransactionBuilder {

    private UUID id;
    private BigDecimal amount;
    private LocalDate date;
    private String description;
    private boolean expense;
    private Tag tag;

    public TransactionBuilderImpl() {
    }

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
    public TransactionBuilder IsExpense(final boolean expense) {
        this.expense = expense;
        return this;
    }

    @Override
    public TransactionBuilder withTag(final Tag tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public TransactionImpl build() {
        var transaction = new TransactionImpl(
                this.id,
                this.amount,
                this.date,
                this.description,
                this.expense,
                this.tag);
        return transaction;
    }
}
