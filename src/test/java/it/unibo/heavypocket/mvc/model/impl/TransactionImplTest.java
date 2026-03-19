package it.unibo.heavypocket.mvc.model.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.TransactionType;

/**
 * Test class for TransactionImpl.
 */
public final class TransactionImplTest {

    private static final UUID ID_EXPENSE = UUID.randomUUID();
    private static final UUID ID_INCOME = UUID.randomUUID();
    private static final BigDecimal AMOUNT = new BigDecimal("100.00");
    private static final BigDecimal NEGATIVE_AMOUNT = AMOUNT.negate();
    private static final String DESCRIPTION = "Test transaction";
    private static final LocalDate DATE = LocalDate.of(2026, 1, 1);
    private static final Tag TAG_EXPENSE = TagEnumImpl.FOOD;
    private static final Tag TAG_INCOME = TagEnumImpl.SALARY;

    private Transaction income;
    private Transaction expense;

    /**
     * Sets up the test fixtures before each test method.
     */
    @BeforeEach
    public void setUp() {
        this.income = Transaction.builder()
                .withId(ID_INCOME)
                .withAmount(AMOUNT)
                .withDate(DATE)
                .withDescription(DESCRIPTION)
                .withType(TransactionType.INCOME)
                .withTag(TAG_INCOME)
                .build();
        this.expense = Transaction.builder()
                .withId(ID_EXPENSE)
                .withAmount(AMOUNT)
                .withDate(DATE)
                .withDescription(DESCRIPTION)
                .withType(TransactionType.EXPENSE)
                .withTag(TAG_EXPENSE)
                .build();
    }

    /**
     * Tests the amount of an expense transaction.
     */
    @Test
    public void testExpenseAmount() {
        assertEquals(NEGATIVE_AMOUNT, expense.getSignedAmount());
    }

    /**
     * Tests the amount of an income transaction.
     */
    @Test
    public void testIncomeAmount() {
        assertEquals(AMOUNT, income.getSignedAmount());
    }

    /**
     * Tests the creation of a transaction.
     */
    @Test
    public void testTransactionCreation() {
        assertNotNull(income);
        assertEquals(ID_INCOME, income.getId());
        assertEquals(AMOUNT, income.getAmount());
        assertEquals(DATE, income.getDate());
        assertEquals(DESCRIPTION, income.getDescription());
        assertEquals(TransactionType.INCOME, income.getType());
        assertEquals(TransactionType.EXPENSE, expense.getType());
        assertEquals(TAG_INCOME, income.getTag());
    }

    /**
     * Tests that the transaction amount must be positive.
     */
    @Test
    public void testTransactionAmountShouldBePositive() {
        assertThrows(IllegalArgumentException.class, () -> {
            Transaction.builder()
                    .withId(ID_INCOME)
                    .withAmount(BigDecimal.ZERO)
                    .withDate(DATE)
                    .withDescription(DESCRIPTION)
                    .withType(TransactionType.INCOME)
                    .withTag(TAG_INCOME)
                    .build();
        });
    }
}
