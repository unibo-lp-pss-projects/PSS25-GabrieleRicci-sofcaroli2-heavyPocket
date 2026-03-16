package it.unibo.heavypocket.mvc.view.panels;

public interface StatisticsBalancePanel extends Panel {

    void setAverageValue(String averageExpense, String averageIncome);

    void setBalance(String balance);
}
