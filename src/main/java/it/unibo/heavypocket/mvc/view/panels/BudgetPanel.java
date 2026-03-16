package it.unibo.heavypocket.mvc.view.panels;

import java.math.BigDecimal;
import java.util.function.Consumer;

public interface BudgetPanel extends Panel {

    void setBudgetStatus(BigDecimal limit, BigDecimal spent, boolean exceeded);

    void setOnUpdateLimit(Consumer<BigDecimal> listener);
}
