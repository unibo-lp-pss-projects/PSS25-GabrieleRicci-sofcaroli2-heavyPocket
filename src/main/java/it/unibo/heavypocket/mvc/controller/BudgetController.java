package it.unibo.heavypocket.mvc.controller;

import java.math.BigDecimal;

public interface BudgetController {

    BigDecimal getBudgetLimit();

    BigDecimal getCurrentSpent();

    boolean isBudgetExceeded();
}
