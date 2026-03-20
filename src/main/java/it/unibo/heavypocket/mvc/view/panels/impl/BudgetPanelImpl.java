package it.unibo.heavypocket.mvc.view.panels.impl;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import java.util.function.Consumer;
import it.unibo.heavypocket.mvc.view.panels.BudgetPanel;

/**
 * Implementation of the BudgetPanel's interface.
 */
public final class BudgetPanelImpl implements BudgetPanel {

    private static final int WIDTH = 110;
    private final Label budgetTitle = new Label("Your budget: ");
    private final Label budgetInfo = new Label();
    private final Label budgetStatus = new Label();
    private final TextField newBudgetField = new TextField();
    private final Button updateButton = new Button("Update budget");
    private Consumer<String> updateLimitListener;
    private final HBox budgetRow = new HBox(8, budgetInfo, budgetStatus);
    private final HBox editRow = new HBox(8, newBudgetField, updateButton);
    private final VBox rootPanel = new VBox(6, budgetTitle, budgetRow, editRow);

    /**
     * Builds the panel UI and connects the update action handler.
     */
    public BudgetPanelImpl() {
        budgetRow.setAlignment(Pos.CENTER);
        editRow.setAlignment(Pos.CENTER);
        rootPanel.setAlignment(Pos.CENTER);
        newBudgetField.setPromptText("Enter new budget");
        newBudgetField.setPrefWidth(WIDTH);
        updateButton.setOnAction(e -> handleUpdateBudget());
    }

    @Override
    public Region getRoot() {
        return rootPanel;
    }

    @Override
    public void setOnUpdateLimit(final Consumer<String> listener) {
        this.updateLimitListener = listener;
    }

    @Override
    public void setBudgetElements(final String limit, final String spent) {
        budgetInfo.setText(spent + " / " + limit);
    }

    @Override
    public void showLimitExceeded(final boolean isExceeded) {
        if (isExceeded) {
            this.budgetStatus.setText("Budget limit exceeded!");
            this.budgetStatus.setTextFill(javafx.scene.paint.Color.RED);
        } else {
            this.budgetStatus.setText("You are within the budget limit");
            this.budgetStatus.setTextFill(javafx.scene.paint.Color.GREEN);
        }
    }

    private void handleUpdateBudget() {
        if (this.updateLimitListener != null) {
            this.updateLimitListener.accept(newBudgetField.getText());
        }
    }
}
