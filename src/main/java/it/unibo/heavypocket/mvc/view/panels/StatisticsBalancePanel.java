package it.unibo.heavypocket.mvc.view.panels;

/**
 * The interface StatisticsBalancePanel.
 */
public interface StatisticsBalancePanel extends Panel {

    /**
     * Sets the average value.
     * 
     * @param averageExpense average of all the expenses into a String.
     * @param averageIncome average of all the incomes into a String.
     */
    void setAverageValue(String averageExpense, String averageIncome);

    /**
     * Sets the balance.
     * 
     * @param balance balance of the account.
     */
    void setBalance(String balance);
}
