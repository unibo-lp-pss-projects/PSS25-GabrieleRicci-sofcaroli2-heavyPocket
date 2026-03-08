package it.unibo.heavypocket.mvc.model.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Transaction;

public class TransactionImplTest {

    private static final UUID ID = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
    private static final BigDecimal AMOUNT = new BigDecimal("100.00");
    private static final BigDecimal NEGATIVE_AMOUNT = AMOUNT.negate();
    private static final String DESCRIPTION = "Test transaction";
    private static final LocalDate DATE = LocalDate.of(2026, 1, 1);
    private static final Tag TAG = new TagImpl("Test tag", "#000000");

    private Transaction transaction;

    @BeforeEach
    public void setUp() {
        this.transaction = new TransactionBuilderImpl()
                .withId(ID)
                .withAmount(AMOUNT)
                .withDate(DATE)
                .withDescription(DESCRIPTION)
                .withIsExpense(true)
                .withTag(TAG)
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
        assertEquals(TAG, transaction.getTag());
    }

    @Test
    public void testTransactionAmountShouldBePositive() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TransactionBuilderImpl()
                    .withId(ID)
                    .withAmount(BigDecimal.ZERO)
                    .withDate(DATE)
                    .withDescription(DESCRIPTION)
                    .withIsExpense(false)
                    .withTag(TAG)
                    .build();
        });
    }

    @Test
    public void testExpenseAmount() {
        assertEquals(NEGATIVE_AMOUNT, transaction.getSignedAmount());
    }

}
