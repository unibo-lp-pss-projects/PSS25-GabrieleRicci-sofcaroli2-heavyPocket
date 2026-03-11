package it.unibo.heavypocket.mvc.model.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import it.unibo.heavypocket.mvc.model.Statistics;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.Tag;
import java.math.RoundingMode;

public final class StatisticsImpl implements Statistics {

    private List<Transaction> transactions;
    private List<Transaction> expenses;
    private BigDecimal expensesCount;

    public StatisticsImpl(final List<Transaction> transactions) {
        this.transactions = transactions;
        this.expenses = transactions.stream().filter(Transaction::isExpense).toList();
        this.expensesCount = new BigDecimal(expenses.size());
    }

    @Override
    public BigDecimal getAverageExpense() {
        final BigDecimal AverageExpense = expenses.stream()
                .map(Transaction::getAmount) // so già che sono tutte negative, quindi non serve signedAmout
                .reduce(BigDecimal.ZERO, BigDecimal::add) // somma tutti i valori insieme
                .divide(expensesCount, 2, RoundingMode.HALF_UP); // dividi per il numero di spese per ottenere la media
        return AverageExpense;
    }  
    
    // @Override
    // public Map<Tag, BigDecimal> getExpenseByTag() {
    //     return null;
    // }     
    
    // @Override           
    // public Map<String, BigDecimal> getStatisticsByMonth() {
    //     return null;
    // }

    
}
