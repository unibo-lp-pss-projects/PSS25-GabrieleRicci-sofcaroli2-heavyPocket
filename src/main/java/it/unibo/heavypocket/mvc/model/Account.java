package it.unibo.heavypocket.mvc.model;

import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDate;
import java.util.UUID;
import java.util.Set;
import java.util.Optional;

/**
 * Interface representing an account.
 */
public interface Account {

    /**
     * Returns the total balance of the account.
     *
     * @return the sum of the amounts of all transactions in the account.
     */
    BigDecimal getTotalBalance();

    /**
     * Returns the set of tags associated with the account.
     * 
     * @return the set of tags associated with the account.
     */
    Set<Tag> getTags();

    /**
     * Returns the budget for the account.
     * 
     * @return the budget for the account.
     */
    Budget getBudget();

    /**
     * Returns the statistics for the account.
     * 
     * @return the statistics for the account.
     */
    Statistics getStatistics();

    /**
     * Returns the list of transactions associated with the account.
     * 
     * @param id the ID of the transaction to search for.
     * @return an optional containing the transaction with the given ID, or an empty Optional if no such transaction is found.
     */
    Optional<Transaction> getTransactionById(UUID id);

    /**
     * Searches for transactions by type.
     * 
     * @param type the type of transactions to search for (EXPENSE or INCOME).
     * @return a list of transactions that match the given type.
     */
    List<Transaction> searchByType(TransactionType type);

    /**
     * Searches for transactions by date.
     * 
     * @param date the date to search for.
     * @return a list of transactions that match with the given date.
     */
    List<Transaction> searchByDate(LocalDate date);

    /**
     * Searches for transactions by tag.
     * 
     * @param tag the tag to search for.
     * @return a list of transactions that match the given tag.
     */
    List<Transaction> searchByTag(Tag tag);

    /**
     * Returns the list of transactions associated with the account.
     * 
     * @return the list of transactions associated with the account.
     */
    List<Transaction> getTransactions();

    /**
     * Adds a transaction to the account.
     * 
     * @param transaction the transaction to add to the account.
     */
    void addTransaction(Transaction transaction);

    /**
     * Edits an existing transaction in the account.
     * 
     * @param id the ID of the transaction to edit.
     * @param newTransaction the updated transaction.
     */
    void editTransaction(UUID id, Transaction newTransaction);

    /**
     * Deletes a transaction from the account.
     * 
     * @param transaction the transaction to delete from the account.
     */
    void deleteTransaction(Transaction transaction);
}
