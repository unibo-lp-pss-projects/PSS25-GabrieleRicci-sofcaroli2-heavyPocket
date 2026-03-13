package it.unibo.heavypocket.mvc.controller;

import java.math.BigDecimal;

public interface BudgetController {

    BigDecimal getBudgetLimit();

    BigDecimal getCurrentSpent();

    void addExpense(BigDecimal amount);

    void updateBudgetLimit(BigDecimal newLimit);

    boolean isBudgetExceeded();

}
