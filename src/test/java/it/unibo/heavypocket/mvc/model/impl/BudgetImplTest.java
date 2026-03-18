package it.unibo.heavypocket.mvc.model.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import it.unibo.heavypocket.mvc.model.Budget;

class BudgetImplTest {

    private static final BigDecimal INITIAL_LIMIT = new BigDecimal("500");

    @Test
    void testInitialValues() {
        final Budget budget = new BudgetImpl(INITIAL_LIMIT);
        assertEquals(INITIAL_LIMIT, budget.getLimit());
    }

    @Test
    void testSetLimit() {
        final Budget budget = new BudgetImpl(INITIAL_LIMIT);
        final BigDecimal newLimit = new BigDecimal("300");

        budget.setLimit(newLimit);

        assertEquals(newLimit, budget.getLimit());
    }

    @Test
    void testConstructorWithZeroLimit() {
        assertThrows(IllegalArgumentException.class, () -> new BudgetImpl(BigDecimal.ZERO));
    }

    @Test
    void testConstructorWithNegativeLimit() {
        assertThrows(IllegalArgumentException.class, () -> new BudgetImpl(new BigDecimal("-1")));
    }

    @Test
    void testSetLimitWithZero() {
        final Budget budget = new BudgetImpl(INITIAL_LIMIT);
        assertThrows(IllegalArgumentException.class, () -> budget.setLimit(BigDecimal.ZERO));
    }

    @Test
    void testSetLimitWithNegativeValue() {
        final Budget budget = new BudgetImpl(INITIAL_LIMIT);
        assertThrows(IllegalArgumentException.class, () -> budget.setLimit(new BigDecimal("-10")));
    }

}
