package it.unibo.heavypocket.mvc.view.panels;

import java.util.function.Consumer;

public interface BudgetPanel extends Panel {

    void setBudgetElements(String limit, String spent);

    void showLimitExceeded(boolean isExceeded);

    void setOnUpdateLimit(Consumer<String> listener);
}
