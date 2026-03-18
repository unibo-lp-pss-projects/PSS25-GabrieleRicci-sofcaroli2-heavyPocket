package it.unibo.heavypocket.mvc.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Interface for building Transaction.
 */
public interface TransactionBuilder {

    /**
     * Sets the ID of the transaction.
     * 
     * @param id the ID of the transaction.
     * @return this builder instance for method chaining.
     */
    TransactionBuilder withId(UUID id);

    /**
     * Sets the amount of the transaction.
     * 
     * @param amount the amount of the transaction.
     * @return this builder instance for method chaining.
     */
    TransactionBuilder withAmount(BigDecimal amount);

    /**
     * Sets the date of the transaction.
     * 
     * @param date the date of the transaction.
     * @return this builder instance for method chaining.
     */
    TransactionBuilder withDate(LocalDate date);

    /**
     * Sets the description of the transaction.
     * 
     * @param description the description of the transaction.
     * @return this builder instance for method chaining.
     */
    TransactionBuilder withDescription(String description);

    /**
     * Sets the type of the transaction.
     * 
     * @param type the type of the transaction (EXPENSE or INCOME).
     * @return this builder instance for method chaining.
     */
    TransactionBuilder withType(TransactionType type);

    /**
     * Sets the tag of the transaction.
     * 
     * @param tag the tag of the transaction.
     * @return this builder instance for method chaining.
     */
    TransactionBuilder withTag(Tag tag);

    /**
     * Builds the Transaction instance.
     * 
     * @return the built Transaction instance.
     */
    Transaction build();
}
