package it.unibo.heavypocket.mvc.model.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.unibo.heavypocket.mvc.model.Statistics;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.TransactionType;
import it.unibo.heavypocket.mvc.model.Tag;

/**
 * Implementation of the Statistic's interface.
 */
public final class StatisticsImpl implements Statistics {

    /**
     * Constructor of the class StatisticsImpl.
     */
    public StatisticsImpl() {
    }

    @Override
    public BigDecimal getAverage(final List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            return BigDecimal.ZERO;
        }
        final BigDecimal transactionsCount = new BigDecimal(transactions.size());
        final BigDecimal averageTransactions = transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(transactionsCount, 2, RoundingMode.HALF_UP);
        return averageTransactions;
    }

    @Override
    public Map<Tag, BigDecimal> getAmountByTag(final List<Transaction> transactions) {
        final Map<Tag, BigDecimal> transactionsByTag = transactions.stream()
                .collect(Collectors.toMap(
                        Transaction::getTag,
                        Transaction::getAmount,
                        BigDecimal::add));
        return transactionsByTag;
    }

    @Override
    public List<Transaction> getExpenses(final List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .toList();
    }

    @Override
    public List<Transaction> getIncomes(final List<Transaction> transactions) {
        return transactions.stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .toList();
    }
}
