package it.unibo.heavypocket.mvc.view.panels.impl;

import java.time.LocalDate;
import java.time.Month;

import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

import it.unibo.heavypocket.mvc.view.panels.StatisticsBalancePanel;

/**
 * Implementation of the StatisticsBalancePanel's interface.
 */
public final class StatisticsBalancePanelImpl implements StatisticsBalancePanel {

    private static final int FONT = 25;
    private static final int SPACING = 7;

    private final VBox rootPanel = new VBox();
    private final Text balanceText = new Text();
    private final Text averageText = new Text();

    private final Text yourBalance = new Text("Your balance:");

    /**
     * Constructor of the class StatisticsImpl.
     */
    public StatisticsBalancePanelImpl() {
        rootPanel.setSpacing(SPACING);
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
    public void setAverageValue(final String averageExpense, final String averageIncome) {
        final Month todayDate = LocalDate.now().getMonth();
        this.averageText.setText(todayDate + " average income: " + averageIncome + "\n" + todayDate
                + " average expense: " + averageExpense);
    }

    @Override
    public void setBalance(final String balance) {
        this.balanceText.setText(balance);
        balanceText.setFont(new Font(FONT));
    }
}
