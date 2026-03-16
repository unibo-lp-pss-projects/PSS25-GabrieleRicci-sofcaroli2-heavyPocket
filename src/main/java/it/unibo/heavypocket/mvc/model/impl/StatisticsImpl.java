package it.unibo.heavypocket.mvc.model.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.stream.Collectors;

import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Statistics;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.TransactionType;

public final class StatisticsImpl implements Statistics {

    public StatisticsImpl() {
    }

    @Override
    public BigDecimal getAverage(final List<Transaction> transactions) {
        final BigDecimal transactionsCount = new BigDecimal(transactions.size());
        final BigDecimal averageTransactions = transactions.stream()
                .map(Transaction::getAmount) // so già che sono tutte negative, quindi non serve signedAmout
                .reduce(BigDecimal.ZERO, BigDecimal::add) // somma tutti i valori insieme
                .divide(transactionsCount, 2, RoundingMode.HALF_UP); // dividi per il numero di spese per ottenere la media
        return averageTransactions;
    }

    @Override
    public Map<Tag, BigDecimal> getExpenseByTag(final List<Transaction> expenses) {
        final Map<Tag, BigDecimal> expenseByTag = expenses.stream()
                .collect(Collectors.toMap(
                        Transaction::getTag,
                        Transaction::getAmount,
                        BigDecimal::add));
        return expenseByTag;
    }

    @Override
    public Map<LocalDate, BigDecimal> getStatisticsByMonth(final List<Transaction> transactions) {
        final LocalDate todayDate = LocalDate.now(); // data di oggi per sapere il mese corrente
        final Map<LocalDate, BigDecimal> statisticsByMonth = transactions.stream()
                .filter(t -> t.getDate().getMonth() == todayDate.getMonth()
                        && t.getDate().getYear() == todayDate.getYear())
                // filtra solo le tranzazioni che sono nel mese e anno della data corrente;
                .collect(Collectors.toMap(
                        Transaction::getDate,
                        Transaction::getAmount,
                        BigDecimal::add));
        return statisticsByMonth;
    }

    @Override
    public List<Transaction> getExpenses(final List<Transaction> transactions) { // ritorna una lista di expense, riutilizzabile per gli altri metodi
        return transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .toList();
    }

    @Override
    public List<Transaction> getIncomes(final List<Transaction> transactions) { // ritorna una lista di income
        return transactions.stream()
                .filter(t -> t.getType() == TransactionType.INCOME)
                .toList();
    }
}
