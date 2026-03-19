package it.unibo.heavypocket.mvc.model.impl;

import java.math.BigDecimal;
import static java.util.Objects.requireNonNull;

import it.unibo.heavypocket.mvc.model.Budget;

/**
 * Budget implementation.
 */
public final class BudgetImpl implements Budget {

    private static final String INVALID_LIMIT_ERROR_MESSAGE = "Limit must be positive and greater than zero";
    private static final String NULL_LIMIT_ERROR_MESSAGE = "Limit cannot be null";

    private BigDecimal limit;

    /**
     * Creates a budget with the given limit.
     * 
     * @param limit the initial budget limit
     * @throws IllegalArgumentException if limit is less than or equal to zero
     * @throws NullPointerException     if limit is null
     */
    public BudgetImpl(final BigDecimal limit) {
        requireNonNull(limit, NULL_LIMIT_ERROR_MESSAGE);
        if (limit.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(INVALID_LIMIT_ERROR_MESSAGE);
        }
        this.limit = limit;
    }

    @Override
    public BigDecimal getLimit() {
        return limit;
    }

    /**
     * Sets the budget limit.
     * 
     * @param newLimit the new budget limit
     * @throws IllegalArgumentException if newLimit is less than or equal to zero
     * @throws NullPointerException     if newLimit is null
     */
    @Override
    public void setLimit(final BigDecimal newLimit) {
        requireNonNull(newLimit, NULL_LIMIT_ERROR_MESSAGE);
        if (newLimit.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(INVALID_LIMIT_ERROR_MESSAGE);
        }
        this.limit = newLimit;
    }
}
