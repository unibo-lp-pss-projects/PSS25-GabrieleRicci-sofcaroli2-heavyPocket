package it.unibo.heavypocket.mvc.view.panels.impl;

import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.LineChart;

import it.unibo.heavypocket.mvc.view.panels.GraphsPanel;

public class GraphsPanelImpl implements GraphsPanel {

    private final VBox rootPanel = new VBox();

    @Override
    public Region getRoot() {
        return this.rootPanel;
    }

    public void setPieChartData() {

    }

    public void setLineChartData() {

    }
}