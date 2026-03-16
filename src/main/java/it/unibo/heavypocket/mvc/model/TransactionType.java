package it.unibo.heavypocket.mvc.model;

public enum TransactionType {
    INCOME,
    EXPENSE;

    public boolean matches(final Transaction transaction) {
        return this == transaction.getType();
    }
}
