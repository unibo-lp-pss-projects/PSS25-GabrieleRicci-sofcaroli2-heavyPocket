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

    @Override
    public BigDecimal getAverage(final List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            return BigDecimal.ZERO;
        }
        final BigDecimal transactionsCount = new BigDecimal(transactions.size());
        return transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(transactionsCount, 2, RoundingMode.HALF_UP);
    }

    @Override
    public Map<Tag, BigDecimal> getAmountByTag(final List<Transaction> transactions) {
        if (transactions.isEmpty()) {
            return Map.of();
        }
        return transactions.stream()
                .collect(Collectors.toMap(
                        Transaction::getTag,
                        Transaction::getAmount,
                        BigDecimal::add));
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
