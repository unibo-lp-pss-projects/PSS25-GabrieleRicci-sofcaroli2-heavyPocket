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
import static org.junit.jupiter.api.Assertions.assertThrows;

import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.TransactionBuilder;
import it.unibo.heavypocket.mvc.model.impl.TagEnumImpl;

public class TransactionImplTest {

    private static final UUID ID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
    private static final BigDecimal AMOUNT = new BigDecimal("100.00");
    private static final BigDecimal NEGATIVE_AMOUNT = AMOUNT.negate();
    private static final String DESCRIPTION = "Test transaction";
    private static final LocalDate DATE = LocalDate.of(2026, 1, 1);
    private static final Tag TAG_EXPENSE = TagEnumImpl.FOOD;
    private static final Tag TAG_INCOME = TagEnumImpl.SALARY;

    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        this.transaction = Transaction.builder()
                .withId(ID)
                .withAmount(AMOUNT)
                .withDate(DATE)
                .withDescription(DESCRIPTION)
                .IsExpense(true)
                .withTag(TAG_EXPENSE)
                .build();
    }

    @Test
    public void testTransactionCreation() {
        assertNotNull(transaction);
        assertEquals(ID, transaction.getId());
        assertEquals(AMOUNT, transaction.getAmount());
        assertEquals(DATE, transaction.getDate());
        assertEquals(DESCRIPTION, transaction.getDescription());
        assertTrue(transaction.isExpense());
        assertEquals(TAG_EXPENSE, transaction.getTag());
    }

    @Test
    public void testTransactionAmountShouldBePositive() {
        assertThrows(IllegalArgumentException.class, () -> {
            Transaction.builder()
                    .withId(ID)
                    .withAmount(BigDecimal.ZERO)
                    .withDate(DATE)
                    .withDescription(DESCRIPTION)
                    .IsExpense(false)
                    .withTag(TAG_INCOME)
                    .build();
        });
    }

    @Test
    public void testExpenseAmount() {
        assertEquals(NEGATIVE_AMOUNT, transaction.getSignedAmount());
    }
}
