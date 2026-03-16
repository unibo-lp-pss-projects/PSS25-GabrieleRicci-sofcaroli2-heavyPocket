package it.unibo.heavypocket.mvc.view.panels.impl;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.LineChart;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.util.List;

import it.unibo.heavypocket.mvc.view.panels.GraphsPanel;

public class GraphsPanelImpl implements GraphsPanel {

    public GraphsPanelImpl() {
        rootPanel.setSpacing(7);
        rootPanel.setAlignment(Pos.CENTER);
        rootPanel.getChildren().addAll(
                pieChart
            );
        setLineChartData();
    }

    private final HBox rootPanel = new HBox();
    private final PieChart pieChart = new PieChart();

    @Override
    public Region getRoot() {
        return this.rootPanel;
    }

    public void setPieChartData(ObservableList<PieChart.Data> pieChartData) {
        this.pieChart.setData(pieChartData);
    }

    public void setLineChartData() {
    }
}
