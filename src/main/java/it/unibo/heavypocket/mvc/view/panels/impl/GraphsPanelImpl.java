package it.unibo.heavypocket.mvc.view.panels.impl;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.chart.PieChart;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;

import it.unibo.heavypocket.mvc.view.panels.GraphsPanel;

/**
 * Implementation of the GraphsPanel's interface.
 */
public final class GraphsPanelImpl implements GraphsPanel {

    private final HBox rootPanel = new HBox();
    private final VBox vBox1 = new VBox();
    private final VBox vBox2 = new VBox();
    private final PieChart pieChartE = new PieChart();
    private final PieChart pieChartI = new PieChart();
    private final Text textE = new Text();
    private final Text textI = new Text();

    /**
     * Constructor of the class GraphsPanelImpl.
     */
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
    }

    @Override
    public Region getRoot() {
        return rootPanel;
    }

    @Override
    public void setPieChartData(final ObservableList<PieChart.Data> pieChartExpense,
            final ObservableList<PieChart.Data> pieChartIncome) {
        if (pieChartExpense.isEmpty()) {
            textE.setText("No data for EXPENSE");
            textE.setFill(Color.GREY);
        } else {
            textE.setText("Piechart for EXPENSE");
            textE.setFill(Color.BLACK);
        }
        if (pieChartIncome.isEmpty()) {
            textI.setText("No data for INCOME");
            textI.setFill(Color.GREY);
        } else {
            textI.setText("Piechart for INCOME");
            textI.setFill(Color.BLACK);
        }
        pieChartE.setData(pieChartExpense);
        pieChartI.setData(pieChartIncome);
    }
}
