package it.unibo.heavypocket.mvc.model.impl;

import static java.util.Objects.requireNonNull;
import java.math.BigDecimal;

import it.unibo.heavypocket.mvc.model.Budget;

public final class BudgetImpl implements Budget {

    private static final String NULL_LIMIT_ERROR_MESSAGE = "Limit cannot be null";
    private static final String INVALID_LIMIT_ERROR_MESSAGE = "Limit must be positive and non-zero";
    // private static final String NULL_AMOUNT_ERROR_MESSAGE = "Amount cannot be null";
    // private static final String INVALID_AMOUNT_ERROR_MESSAGE = "Amount must be positive and non-zero";

    private BigDecimal limit;
    // private BigDecimal currentSpent;

    public BudgetImpl(final BigDecimal limit) {
        requireNonNull(limit, NULL_LIMIT_ERROR_MESSAGE);
        if (limit.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(INVALID_LIMIT_ERROR_MESSAGE);
        }
        this.limit = limit;
        // this.currentSpent = BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getLimit() {
        return this.limit;
    }

    @Override
    public void setLimit(final BigDecimal newLimit) {
        requireNonNull(newLimit, NULL_LIMIT_ERROR_MESSAGE);
        if (newLimit.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(INVALID_LIMIT_ERROR_MESSAGE);
        }
        this.limit = newLimit;
    }

    // @Override
    // public BigDecimal getCurrentSpent() {
    //     return this.currentSpent;
    // }

    // @Override
    // public void addExpense(final BigDecimal amount) {
    //     requireNonNull(amount, NULL_AMOUNT_ERROR_MESSAGE);
    //     if (amount.compareTo(BigDecimal.ZERO) <= 0) {
    //         throw new IllegalArgumentException(INVALID_AMOUNT_ERROR_MESSAGE);
    //     }
    //     this.currentSpent = this.currentSpent.add(amount);
    // }


    // @Override
    // public boolean isExceeded() {
    //     return this.currentSpent.compareTo(this.limit) > 0;
    // }
}
