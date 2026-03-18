package it.unibo.heavypocket.mvc.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import it.unibo.heavypocket.mvc.model.impl.TransactionBuilderImpl;

/**
 * Interface for transaction. It includes amount, date,
 * description, type, and tag. The interface also provides a builder
 * for creating instances of Transaction.
 */
public interface Transaction {

    /**
     * Returns the id of the transaction.
     * 
     * @return the id of the transaction.
     */
    UUID getId();

    /**
     * Returns the amount of the transaction.
     *
     * @return the amount of the transaction.
     */
    BigDecimal getAmount();

    /**
     * Returns the date of the transaction.
     * 
     * @return the date of the transaction.
     */
    BigDecimal getSignedAmount();

    /**
     * Returns the date of the transaction.
     * 
     * @return the date of the transaction.
     */
    LocalDate getDate();

    /**
     * Returns the description of the transaction.
     * 
     * @return the description of the transaction.
     */
    String getDescription();

    /**
     * Returns the type of the transaction.
     * 
     * @return the type of the transaction.
     */
    TransactionType getType();

    /**
     * Returns the tag of the transaction.
     * 
     * @return the tag of the transaction.
     */
    Tag getTag();

    /**
     * Static method for creating a new TransactionBuilder instance.
     * 
     * @return a new TransactionBuilder instance.
     */
    static TransactionBuilder builder() {
        return new TransactionBuilderImpl();
    }
}
