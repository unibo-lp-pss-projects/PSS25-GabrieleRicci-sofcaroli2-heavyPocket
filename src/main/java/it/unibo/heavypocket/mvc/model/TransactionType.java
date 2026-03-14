package it.unibo.heavypocket.mvc.model;

import it.unibo.heavypocket.mvc.model.Transaction;

public enum TransactionType {
    ALL,
    INCOME,
    EXPENSE;

    public boolean matches(final Transaction transaction) {
        return this == ALL || this == transaction.getType();
    }
}