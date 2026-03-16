package it.unibo.heavypocket.mvc.controller.impl;

import java.math.BigDecimal;

import it.unibo.heavypocket.mvc.controller.BudgetController;
import it.unibo.heavypocket.mvc.model.Budget;

import java.util.Objects;

public final class BudgetControllerImpl implements BudgetController {

    private final Budget budget;

    public BudgetControllerImpl(final Budget budget) {
        this.budget = Objects.requireNonNull(budget);
    }

    @Override
    public BigDecimal getBudgetLimit() {
        return this.budget.getLimit();
    }

    @Override
    public BigDecimal getCurrentSpent() {
        return this.budget.getCurrentSpent();
    }

    @Override
    public void addExpense(final BigDecimal amount) {
        this.budget.addExpense(amount);
    }

    @Override
    public void updateBudgetLimit(final BigDecimal newLimit) {
        this.budget.setLimit(newLimit);
    }

    @Override
    public boolean isBudgetExceeded() {
        return this.budget.isExceeded();
    }
}
