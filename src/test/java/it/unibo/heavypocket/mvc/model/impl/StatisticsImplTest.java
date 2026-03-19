package it.unibo.heavypocket.mvc.model.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.heavypocket.mvc.model.Statistics;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.TransactionType;
import it.unibo.heavypocket.mvc.model.Tag;

/**
 * Class for testing the StatisticsImpl.
 */
final class StatisticsImplTest {

    private static final UUID ID_1 = UUID.randomUUID();
    private static final UUID ID_2 = UUID.randomUUID();
    private static final BigDecimal AMOUNT1 = new BigDecimal("10.00");
    private static final BigDecimal AMOUNT2 = new BigDecimal("40.00");
    private static final String DESCRIPTION = "Test transaction";
    private static final LocalDate DATE = LocalDate.of(2026, 1, 1);
    private static final Tag TAG_1 = TagEnumImpl.FOOD;
    private static final Tag TAG_2 = TagEnumImpl.SALARY;
    private static final BigDecimal AVERAGE = new BigDecimal("25.00");

    private Transaction transaction1;
    private Transaction transaction2;
    private List<Transaction> transactionsList;
    private List<Transaction> transactionsListEmpty;
    private final Statistics statistics = new StatisticsImpl();

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    public void setUp() {
        this.transaction1 = Transaction.builder()
                .withId(ID_1)
                .withAmount(AMOUNT1)
                .withDate(DATE)
                .withDescription(DESCRIPTION)
                .withType(TransactionType.INCOME)
                .withTag(TAG_1)
                .build();
        this.transaction2 = Transaction.builder()
                .withId(ID_2)
                .withAmount(AMOUNT2)
                .withDate(DATE)
                .withDescription(DESCRIPTION)
                .withType(TransactionType.EXPENSE)
                .withTag(TAG_2)
                .build();
        this.transactionsList = List.of(transaction1, transaction2);
        this.transactionsListEmpty = List.of();
    }

    /**
     * Tests that the average is zero when the transaction list is empty.
     */
    @Test
    void testListEmpty() {
        assertEquals(BigDecimal.ZERO, statistics.getAverage(transactionsListEmpty));
    }

    /**
     * Tests that the average calculation is correct for a list of transactions.
     */
    @Test
    void testAverage() {
        assertEquals(AVERAGE, statistics.getAverage(transactionsList));
    }

    /**
     * Tests that the income filtering returns only transactions of type INCOME.
     */
    @Test
    void testgetIncomes() {
        assertEquals(List.of(transaction1), statistics.getIncomes(transactionsList));
    }

    /**
     * Tests that the income filtering returns only transactions of type EXPENSE.
     */
    @Test
    void testgetExpense() {
        assertEquals(List.of(transaction2), statistics.getExpenses(transactionsList));
    }
}
