package it.unibo.heavypocket.mvc.controller;

import java.util.UUID;

import it.unibo.heavypocket.mvc.DTO.FiltersDTO;
import it.unibo.heavypocket.mvc.DTO.TransactionDTO;

/**
 * The interface AccountController.
 */
public interface AccountController {

    /**
     * Show the total balance of the account.
     */
    void showTotalBalance();

    /**
     * Show all the transactions.
     */
    void showTransactions();

    /**
     * Show list of tags.
     */
    void showTags();

    /**
     * Allows to search on transactions by type, date and tag.
     * 
     * @param filters search criteria.
     */
    void search(FiltersDTO filters);

    /**
     * Allows to add a transaction.
     * 
     * @param transactionDTO the data of the transaction to add.
     */
    void addTransaction(TransactionDTO transactionDTO);

    /**
     * Gets an existing transaction in the view.
     * 
     * @param id reference of transaction to edit.
     */
    void callToEditTransaction(UUID id);

    /**
     * Update the edited transaction with new data.
     * 
     * @param id reference of transaction edited.
     * @param transactionDTO the new data.
     */
    void editTransaction(UUID id, TransactionDTO transactionDTO);

    /**
     * Allows to delete a transaction.
     * 
     * @param id reference of transaction to delete.
     */
    void deleteTransaction(UUID id);

    /**
     * Show budget limit and how much was spent this month.
     */
    void showBudgetElements();

    /**
     * Updates budget limit and checks if it is exceeded.
     * 
     * @param newLimit the new budget limit value.
     */
    void updateBudgetLimit(String newLimit);

    /**
     * Checks and show if budget limit is exceeded.
     */
    void isBudgetExceeded();

    /**
     * Set and show the monthly averege for incomes and expenses.
     */
    void setAverageValue();

    /**
     * Set data grouped by tag for pie charts.
     */
    void setPieChartData();
}
