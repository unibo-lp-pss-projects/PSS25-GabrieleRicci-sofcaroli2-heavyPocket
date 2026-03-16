package it.unibo.heavypocket.mvc.view.panels.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import javafx.scene.layout.VBox;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;

import it.unibo.heavypocket.mvc.model.Statistics;
import it.unibo.heavypocket.mvc.view.panels.StatisticsBalancePanel;

public class StatisticsBalancePanelImpl implements StatisticsBalancePanel {

    private final VBox rootPanel = new VBox();
    private final Text balanceText = new Text();
    private final Text averageText = new Text();

    public StatisticsBalancePanelImpl() {
        rootPanel.setSpacing(10);
        rootPanel.getChildren().addAll(
                averageText,
                balanceText);
    }

    @Override
    public Region getRoot() {
        return this.rootPanel;
    }

    @Override
    public void setAverageValue(String averageExpense, String averageIncome) {
        final Month todayDate = LocalDate.now().getMonth(); // data di oggi per sapere il mese corrente
        this.averageText.setText(todayDate + " average income: " + averageIncome + "\n" + todayDate
                + " average expense: " + averageExpense);
    }

    @Override
    public void setBalance(final String balance) {
        this.balanceText.setText("Your balance: " + balance);
    }
}