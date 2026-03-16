package it.unibo.heavypocket.mvc.view.panels.impl;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.chart.PieChart;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

import it.unibo.heavypocket.mvc.view.panels.GraphsPanel;

public final class GraphsPanelImpl implements GraphsPanel {

    private final HBox rootPanel = new HBox();
    private final PieChart pieChart = new PieChart();

    public GraphsPanelImpl() {
        rootPanel.setSpacing(7);
        rootPanel.setAlignment(Pos.CENTER);
        rootPanel.getChildren().addAll(
                pieChart
            );
        setLineChartData();
    }

    @Override
    public Region getRoot() {
        return this.rootPanel;
    }

    public void setPieChartData(final ObservableList<PieChart.Data> pieChartData) {
        this.pieChart.setData(pieChartData);
    }

    public void setLineChartData() {
    }
}
