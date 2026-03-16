package it.unibo.heavypocket.mvc.view.panels.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

import it.unibo.heavypocket.mvc.model.Statistics;
import it.unibo.heavypocket.mvc.view.panels.StatisticsBalancePanel;

public class StatisticsBalancePanelImpl implements StatisticsBalancePanel {

    private final VBox rootPanel = new VBox();
    private final Text balanceText = new Text();
    private final Text averageText = new Text();

    private final Text yourBalance = new Text ("Your balance:");

    public StatisticsBalancePanelImpl() {
        rootPanel.setSpacing(7);
        rootPanel.setAlignment(Pos.CENTER);
        rootPanel.getChildren().addAll(
                yourBalance,
                balanceText,
                averageText);
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
        this.balanceText.setText(balance);
        balanceText.setFont(new Font(25));
    }
}