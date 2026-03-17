package it.unibo.heavypocket.mvc.view.panels.impl;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

import java.util.function.Consumer;

import it.unibo.heavypocket.mvc.view.panels.BudgetPanel;

public final class BudgetPanelImpl implements BudgetPanel {

    private final VBox rootPanel = new VBox();
    private final TextField budgetField = new TextField();
    private final Button updateButton = new Button("Update budget");
    private final Text currentSpentLabel = new Text();
    private final Text limitLabel = new Text();
    private final Text separator = new Text(" / ");
    private final Text budgetStatusLabel = new Text();
    private Consumer<String> updateLimitListener;

    public BudgetPanelImpl() {
        this.initializeBudgetPanel();

        this.rootPanel.getChildren().addAll(
                budgetField,
                updateButton,
                currentSpentLabel,
                separator,
                limitLabel,
                budgetStatusLabel);
    }

    @Override
    public Region getRoot() {
        return this.rootPanel;
    }

    @Override
    public void setOnUpdateLimit(final Consumer<String> listener) {
        this.updateLimitListener = listener;
    }

    @Override
    public void setBudgetElements(final String limit, final String spent) {
        this.limitLabel.setText(limit);
        this.currentSpentLabel.setText(spent);
    }

    @Override
    public void showLimitExceeded() {
        this.budgetStatusLabel.setText("Budget limit exceeded!");
        this.budgetStatusLabel.setFill(Color.RED);
    }

    @Override
    public void showLimitNotExceeded() {
        this.budgetStatusLabel.setText("You are within the budget limit");
        this.budgetStatusLabel.setFill(Color.GREEN);
    }

    private void initializeBudgetPanel() {
        this.rootPanel.setSpacing(10);
        this.budgetField.setPromptText("Enter new monthly budget");
        this.updateButton.setOnAction(event -> this.handleUpdateBudget());
    }

    private void handleUpdateBudget() {
        if (this.updateLimitListener != null) {
            this.updateLimitListener.accept(budgetField.getText());
        }
    }
}
