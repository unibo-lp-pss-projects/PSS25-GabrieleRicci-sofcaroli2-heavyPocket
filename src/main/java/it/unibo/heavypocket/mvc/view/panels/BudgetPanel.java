package it.unibo.heavypocket.mvc.view.panels;

import java.util.function.Consumer;

/**
 * Interface for displaying and editing budget information.
 */
public interface BudgetPanel extends Panel {

    /**
     * Updates the displayed budget summary.
     * 
     * @param limit the budget limit.
     * @param spent the amount spent.
     */
    void setBudgetElements(String limit, String spent);

    /**
     * Shows the visual status of the budget.
     * 
     * @param isExceeded true if the limit is exceeded, false otherwise.
     */
    void showLimitExceeded(boolean isExceeded);

    /**
     * Registers the callback used when the user requests a budget update.
     * 
     * @param listener callback receiving the new limit text.
     */
    void setOnUpdateLimit(Consumer<String> listener);
}
