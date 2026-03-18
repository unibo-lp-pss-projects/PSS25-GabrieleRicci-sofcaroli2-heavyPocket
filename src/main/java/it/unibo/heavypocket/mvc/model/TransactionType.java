package it.unibo.heavypocket.mvc.model;

/**
 * Enum representing the type of a transaction: INCOME or EXPENSE.
 */
public enum TransactionType {
    INCOME,
    EXPENSE;

    /**
     * Checks if the given transaction matches this transaction type.
     *
     * @param transaction the transaction to check
     * @return true if the transaction matches this type, false otherwise
     */
    public boolean matches(final Transaction transaction) {
        return this == transaction.getType();
    }
}
