package it.unibo.heavypocket.mvc.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * The interface Statistics.
 */
public interface Statistics {

    /**
     * Get the average.
     * 
     * @param transactions list of transactions to calculate the average.
     * @return the average of the sum of all the transaction's amount.
     */
    BigDecimal getAverage(List<Transaction> transactions);

    /**
     * Get a map (tag, amount) with each tag to the sum of its transaction amounts.
     * 
     * @param transactions list of transaction to group by tag.
     * @return a map with tags and relative sum of the transaction amounts.
     */
    Map<Tag, BigDecimal> getAmountByTag(List<Transaction> transactions);

    /**
     * Get a list of transactions that are expenses.
     * 
     * @param transactions list of transactions by expenses.
     * @return a list of expenses.
     */
    List<Transaction> getExpenses(List<Transaction> transactions);

    /**
     * Get a list of transactions that are incomes.
     * 
     * @param transactions list of transactions by incomes.
     * @return a list of incomes.
     */
    List<Transaction> getIncomes(List<Transaction> transactions);
}
