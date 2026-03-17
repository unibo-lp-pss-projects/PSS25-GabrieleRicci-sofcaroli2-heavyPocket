package it.unibo.heavypocket.mvc.model.impl;

import java.math.BigDecimal;

import it.unibo.heavypocket.mvc.model.Budget;

public final class BudgetImpl implements Budget {

    private static final String INVALID_LIMIT_ERROR_MESSAGE = "Limit must be positive and greater than zero";

    private BigDecimal limit;

    public BudgetImpl(final BigDecimal limit) {
        if (limit.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(INVALID_LIMIT_ERROR_MESSAGE);
        }
        this.limit = limit;
    }

    @Override
    public BigDecimal getLimit() {
        return this.limit;
    }

    @Override
    public void setLimit(final BigDecimal newLimit) {
        if (newLimit.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(INVALID_LIMIT_ERROR_MESSAGE);
        }
        this.limit = newLimit;
    }
}
