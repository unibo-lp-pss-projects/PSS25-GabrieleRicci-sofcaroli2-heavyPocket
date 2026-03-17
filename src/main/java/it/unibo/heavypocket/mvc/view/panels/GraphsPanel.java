package it.unibo.heavypocket.mvc.view.panels;

import javafx.collections.ObservableList;

import javafx.scene.chart.PieChart;

public interface GraphsPanel extends Panel {

    void setPieChartData(ObservableList<PieChart.Data> pieChartExpense, ObservableList<PieChart.Data> pieChartIncome);
}
