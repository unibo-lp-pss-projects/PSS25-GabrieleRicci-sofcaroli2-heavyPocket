package it.unibo.heavypocket.mvc.view.panels.impl;

import java.time.LocalDate;
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
    private final Text averageText = new Text("Your avereges of " + LocalDate.now().getMonth());
    private final Text yourBalance = new Text("Your balance:");
    private final Text averagesValue = new Text();

    /**
     * Constructor of the class StatisticsBalancePanelImpl.
     */
    public StatisticsBalancePanelImpl() {
        rootPanel.setSpacing(SPACING);
        rootPanel.setAlignment(Pos.CENTER);
        rootPanel.getChildren().addAll(
                yourBalance,
                balanceText,
                averageText,
                averagesValue);
    }

    @Override
    public Region getRoot() {
        return rootPanel;
    }

    @Override
    public void setAverageValue(final String averageExpense, final String averageIncome) {
        averagesValue.setText("Average of incomes: " + averageIncome + "\n" + "Average of expenses: " + averageExpense);
    }

    @Override
    public void setBalance(final String balance) {
        balanceText.setText(balance);
        balanceText.setFont(new Font(FONT));
    }
}
