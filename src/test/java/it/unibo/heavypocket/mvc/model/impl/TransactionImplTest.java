package it.unibo.heavypocket.mvc.model.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.TransactionBuilder;
import it.unibo.heavypocket.mvc.model.impl.TagEnumImpl;

public class TransactionImplTest {

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

    @BeforeEach
    public void setUp() {
        this.income = Transaction.builder()
                .withId(ID_INCOME)
                .withAmount(AMOUNT)
                .withDate(DATE)
                .withDescription(DESCRIPTION)
                .IsExpense(false)
                .withTag(TAG_INCOME)
                .build();
        this.expense = Transaction.builder()
                .withId(ID_EXPENSE)
                .withAmount(AMOUNT)
                .withDate(DATE)
                .withDescription(DESCRIPTION)
                .IsExpense(true)
                .withTag(TAG_EXPENSE)
                .build();
    }

    @Test
    public void testExpenseAmount() {
        assertEquals(NEGATIVE_AMOUNT, expense.getSignedAmount());
    }

    @Test
    public void testIncomeAmount() {
        assertEquals(AMOUNT, income.getSignedAmount());
    }

    @Test
    public void testTransactionCreation() {
        assertNotNull(income);
        assertEquals(ID_INCOME, income.getId());
        assertEquals(AMOUNT, income.getAmount());
        assertEquals(DATE, income.getDate());
        assertEquals(DESCRIPTION, income.getDescription());
        assertFalse(income.isExpense());
        assertTrue(expense.isExpense());
        assertEquals(TAG_INCOME, income.getTag());
    }

    @Test
    public void testTransactionAmountShouldBePositive() {
        assertThrows(IllegalArgumentException.class, () -> {
            Transaction.builder()
                    .withId(ID_INCOME)
                    .withAmount(BigDecimal.ZERO)
                    .withDate(DATE)
                    .withDescription(DESCRIPTION)
                    .IsExpense(false)
                    .withTag(TAG_INCOME)
                    .build();
        });
    }
}
