package it.unibo.heavypocket.mvc.controller.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import it.unibo.heavypocket.mvc.controller.StatisticsController;
import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.impl.StatisticsImpl;

public final class StatisticsControllerImpl implements StatisticsController {

    private final List<Transaction> transactions;
    private final List<Transaction> expenses;
    private final StatisticsImpl statistics;

    public StatisticsControllerImpl(final List<Transaction> transactions, final StatisticsImpl statistics) {
        this.transactions = transactions;
        this.expenses = getExpenses();
        this.statistics = statistics;
    }

    @Override
    public BigDecimal getAverageValue() {
        return statistics.getAverageExpense(this.expenses);
    }

    @Override
    public Map<Tag, BigDecimal> getPieChartData() {
        return statistics.getExpenseByTag(this.expenses);
    }

    @Override
    public Map<LocalDate, BigDecimal> getLineChartData() {
        return statistics.getStatisticsByMonth(this.transactions);
    }

    private List<Transaction> getExpenses() { // metodo per filtrare del uscite da una lista di transactions
        return this.transactions.stream().filter(Transaction::isExpense).toList();
    }

}
