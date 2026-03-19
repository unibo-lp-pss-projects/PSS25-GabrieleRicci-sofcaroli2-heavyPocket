package it.unibo.heavypocket.mvc.view.panels.impl;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.chart.PieChart;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

import it.unibo.heavypocket.mvc.view.panels.GraphsPanel;

public final class GraphsPanelImpl implements GraphsPanel {

    private final HBox rootPanel = new HBox();
    private final VBox vBox1 = new VBox();
    private final VBox vBox2 = new VBox();
    private final PieChart pieChartE = new PieChart();
    private final PieChart pieChartI = new PieChart();
    private final Text textE = new Text("Piechart for EXPENSE");
    private final Text textI = new Text("Piechart for INCOME");

    public GraphsPanelImpl() {
        rootPanel.setSpacing(10);
        rootPanel.setAlignment(Pos.CENTER);
        vBox1.setAlignment(Pos.CENTER);
        vBox2.setAlignment(Pos.CENTER);
        vBox1.getChildren().addAll(
                textE,
                pieChartE);
        vBox2.getChildren().addAll(
                textI,
                pieChartI);
        rootPanel.getChildren().addAll(
                vBox1,
                vBox2);
        setLineChartData();
    }

    @Override
    public Region getRoot() {
        return rootPanel;
    }

    public void setPieChartData(final ObservableList<PieChart.Data> pieChartExpense,
            final ObservableList<PieChart.Data> pieChartIncome) {
        pieChartE.setData(pieChartExpense);
        pieChartI.setData(pieChartIncome);
    }

    public void setLineChartData() {
    }
}
