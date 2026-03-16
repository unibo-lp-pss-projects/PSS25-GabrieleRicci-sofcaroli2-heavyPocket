package it.unibo.heavypocket.mvc.model.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import it.unibo.heavypocket.mvc.model.Budget;

class BudgetImplTest {

    private static final BigDecimal INITIAL_LIMIT = new BigDecimal("500");

    @Test
    void testInitialValues() {
        final Budget budget = new BudgetImpl(INITIAL_LIMIT);
        assertEquals(INITIAL_LIMIT, budget.getLimit());
        assertEquals(BigDecimal.ZERO, budget.getCurrentSpent());
    }

    @Test
    void testAddExpense() {
        final Budget budget = new BudgetImpl(INITIAL_LIMIT);
        budget.addExpense(new BigDecimal("100"));
        assertEquals(new BigDecimal("100"), budget.getCurrentSpent());
    }

    @Test
    void testBudgetExceeded() {
        final Budget budget = new BudgetImpl(INITIAL_LIMIT);
        budget.addExpense(new BigDecimal("600"));
        assertTrue(budget.isExceeded());
    }

    @Test
    void testConstructorRejectsZeroLimit() {
        assertThrows(IllegalArgumentException.class, () -> new BudgetImpl(BigDecimal.ZERO));
    }

    @Test
    void testAddExpenseRejectsNegativeAmount() {
        final Budget budget = new BudgetImpl(INITIAL_LIMIT);
        assertThrows(IllegalArgumentException.class, () -> budget.addExpense(new BigDecimal("-10")));
    }

    @Test
    void testEqualToLimitIsNotExceeded() {
        final Budget budget = new BudgetImpl(INITIAL_LIMIT);
        budget.addExpense(INITIAL_LIMIT);
        assertFalse(budget.isExceeded());
    }

    @Test
    void testConstructorRejectsNullLimit() {
        assertThrows(NullPointerException.class, () -> new BudgetImpl(null));
    }

    @Test
    void testAddExpenseRejectsZeroAmount() {
        final Budget budget = new BudgetImpl(INITIAL_LIMIT);
        assertThrows(IllegalArgumentException.class, () -> budget.addExpense(BigDecimal.ZERO));
    }

    @Test
    void testAddExpenseRejectsNullAmount() {
        final Budget budget = new BudgetImpl(INITIAL_LIMIT);
        assertThrows(NullPointerException.class, () -> budget.addExpense(null));
    }

    @Test
    void testSetLimitRejectsNull() {
        final Budget budget = new BudgetImpl(INITIAL_LIMIT);
        assertThrows(NullPointerException.class, () -> budget.setLimit(null));
    }

    @Test
    void testSetLimitRejectsZero() {
        final Budget budget = new BudgetImpl(INITIAL_LIMIT);
        assertThrows(IllegalArgumentException.class, () -> budget.setLimit(BigDecimal.ZERO));
    }

    @Test
    void testSetLimitRejectsNegative() {
        final Budget budget = new BudgetImpl(INITIAL_LIMIT);
        assertThrows(IllegalArgumentException.class, () -> budget.setLimit(new BigDecimal("-1")));
    }

    @Test
    void testLoweringLimitBelowCurrentSpentMakesBudgetExceeded() {
        final Budget budget = new BudgetImpl(INITIAL_LIMIT);
        budget.addExpense(new BigDecimal("400"));
        budget.setLimit(new BigDecimal("300"));
        assertTrue(budget.isExceeded());
        assertEquals(new BigDecimal("400"), budget.getCurrentSpent());
        assertEquals(new BigDecimal("300"), budget.getLimit());
    }
}
