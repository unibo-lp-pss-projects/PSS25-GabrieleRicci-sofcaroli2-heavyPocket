package it.unibo.heavypocket.mvc.view.panels.impl;

import java.math.BigDecimal;
import java.util.function.Consumer;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import it.unibo.heavypocket.mvc.view.panels.BudgetPanel;

public final class BudgetPanelImpl implements BudgetPanel {

    private static final String EURO_SUFFIX = " €";
    private static final String STATUS_EXCEEDED = "Budget exceeded";
    private static final String STATUS_OK = "Budget respected";
    private static final String STATUS_INVALID_INPUT = "Invalid budget value";

    private final VBox rootPanel = new VBox();
    private final Label budgetLimitLabel = new Label();
    private final Label currentSpentLabel = new Label();
    private final Label budgetStatusLabel = new Label();
    private final TextField newBudgetField = new TextField();
    private final Button updateButton = new Button("Update budget");
    private Consumer<BigDecimal> updateLimitListener;

    public BudgetPanelImpl() {
        this.initializeBudgetPanel();
    }

    @Override
    public Region getRoot() {
        return this.rootPanel;
    }

    @Override
    public void setBudgetStatus(final BigDecimal limit, final BigDecimal spent, final boolean exceeded) {
        this.budgetLimitLabel.setText("Monthly limit: " + limit + EURO_SUFFIX);
        this.currentSpentLabel.setText("Current spent: " + spent + EURO_SUFFIX);
        this.budgetStatusLabel.setText(exceeded ? STATUS_EXCEEDED : STATUS_OK);
    }

    @Override
    public void setOnUpdateLimit(final Consumer<BigDecimal> listener) {
        this.updateLimitListener = listener;
    }

    private void initializeBudgetPanel() {
        this.rootPanel.setSpacing(8);
        this.rootPanel.setPadding(new Insets(10));
        this.newBudgetField.setPromptText("New monthly budget (e.g. 500.00)");

        final HBox updateBudgetBox = new HBox();
        updateBudgetBox.setSpacing(8);
        updateBudgetBox.getChildren().addAll(this.newBudgetField, this.updateButton);

        this.updateButton.setOnAction(event -> this.handleUpdateBudget());

        this.rootPanel.getChildren().addAll(
                this.budgetLimitLabel,
                this.currentSpentLabel,
                this.budgetStatusLabel,
                updateBudgetBox);
    }

    private void handleUpdateBudget() {
        if (this.updateLimitListener == null) {
            return;
        }
        try {
            final BigDecimal newLimit = new BigDecimal(this.newBudgetField.getText().trim());
            if (newLimit.compareTo(BigDecimal.ZERO) <= 0) {
                throw new NumberFormatException("Budget value must be positive");
            }
            this.updateLimitListener.accept(newLimit);
            this.newBudgetField.clear();
        } catch (final NumberFormatException ex) {
            this.budgetStatusLabel.setText(STATUS_INVALID_INPUT);
        }
    }
}
