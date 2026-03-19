package it.unibo.heavypocket.mvc.view.panels;

import javafx.collections.ObservableList;

import javafx.scene.chart.PieChart;

/**
 * The interface GraphsPanel that exteds Panel.
 */
public interface GraphsPanel extends Panel {

    /**
     * Set data grouped by tag for pie charts.
     * 
     * @param pieChartExpense list of piechart data in expenses.
     * @param pieChartIncome list of piechart data in incomes.
     */
    void setPieChartData(ObservableList<PieChart.Data> pieChartExpense, ObservableList<PieChart.Data> pieChartIncome);
}
