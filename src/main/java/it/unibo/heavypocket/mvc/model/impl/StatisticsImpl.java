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

public final class StatisticsImpl implements Statistics {

    private List<Transaction> transactions;
    private List<Transaction> expenses;
    private BigDecimal expensesCount;

    public StatisticsImpl(final List<Transaction> transactions) {
        this.transactions = transactions;
        this.expenses = transactions.stream().filter(Transaction::isExpense).toList();
        this.expensesCount = new BigDecimal(this.expenses.size());
    }

    @Override
    public BigDecimal getAverageExpense() {
        final BigDecimal averageExpense = expenses.stream()
                .map(Transaction::getAmount) // so già che sono tutte negative, quindi non serve signedAmout
                .reduce(BigDecimal.ZERO, BigDecimal::add) // somma tutti i valori insieme
                .divide(expensesCount, 2, RoundingMode.HALF_UP); // dividi per il numero di spese per ottenere la media
        return averageExpense;
    }

    @Override
    public Map<Tag, BigDecimal> getExpenseByTag() {
        final Map<Tag, BigDecimal> expenseByTag = expenses.stream()
                .collect(Collectors.toMap(
                        Transaction::getTag,
                        Transaction::getAmount,
                        BigDecimal::add));
        return expenseByTag;
    }

    @Override
    public Map<LocalDate, BigDecimal> getStatisticsByMonth() {
        final LocalDate todayDate = LocalDate.now(); // data di oggi per sapere il mese corrente
        final Map<LocalDate, BigDecimal> statisticsByMonth = transactions.stream()
                .filter(t -> t.getDate().getMonth() == todayDate.getMonth()
                        && t.getDate().getYear() == todayDate.getYear())
                // filtra solo le tranzazioni che sono nle mese e anno della data corrente;
                .collect(Collectors.toMap(
                        Transaction::getDate,
                        Transaction::getAmount,
                        BigDecimal::add));
        return statisticsByMonth;
    }
}
