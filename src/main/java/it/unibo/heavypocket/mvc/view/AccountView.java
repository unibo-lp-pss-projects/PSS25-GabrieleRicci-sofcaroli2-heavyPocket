package it.unibo.heavypocket.mvc.view;

import javafx.stage.Stage;

import java.util.List;
import java.util.UUID;
import java.math.BigDecimal;
import java.util.Map;

import it.unibo.heavypocket.mvc.controller.AccountController;
import it.unibo.heavypocket.mvc.dto.TransactionDTO;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.Tag;

/**
 * The interface AccountView.
 */
public interface AccountView {

    /**
     * Initializes the main VBox and connects panel events to the controller.
     * 
     * @param primaryStage the main JavaFX stage for the application.
     */
    void start(Stage primaryStage);

    /**
     * Sets the controller to handle user interactions.
     * 
     * @param controller controller of the app.
     */
    void setController(AccountController controller);

    /**
     * Gets the controller.
     * 
     * @return the controller in view.
     */
    AccountController getController();

    /**
     * Show transaction list.
     * 
     * @param transactions list of transaction to show.
     */
    void showTransactionList(List<Transaction> transactions);

    /**
     * Allows to search on transactions by type, date and tag.
     * 
     * @param tags search criteria
     */
    void showTagList(List<Tag> tags);

    /**
     * Show total balance.
     * 
     * @param balance total balance to show.
     */
    void showBalance(String balance);

    /**
     * Opens the edit mode for a specific transaction in the input panel.
     * 
     * @param id             reference of transaction to delete.
     * @param transactionDTO the new data.
     */
    void showEditTransaction(UUID id, TransactionDTO transactionDTO);

    /**
     * Shows a popup with an error message.
     * 
     * @param error the message to display to the user.
     */
    void showError(String error);

    /**
     * Show the average income and expenses in the statistics panel.
     * 
     * @param averageExpense average of expenses.
     * @param averageIncome  average of incomes.
     */
    void showAverage(String averageExpense, String averageIncome);

    /**
     * Converts map into JavaFX PieChart data and updates the graph panel.
     * 
     * @param pieChartExpense search criteria
     * @param pieChartIncome  search criteria
     */
    void showPieChartData(Map<Tag, BigDecimal> pieChartExpense, Map<Tag, BigDecimal> pieChartIncome);

    /**
     * Updates the budget panel with the limit and current spending.
     * 
     * @param limit budget limit.
     * @param spent amounth for the current month.
     */
    void showBudgetElements(String limit, String spent);

    /**
     * Notifies the budget panel if the spending limit has been exceeded.
     * 
     * @param isExceeded true if the budget is over the limit.
     */
    void showLimitExceeded(boolean isExceeded);
}
