package it.unibo.heavypocket.mvc.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface Statistics {

    BigDecimal getAverage(List<Transaction> transactions);

    List<Transaction> getExpenses(List<Transaction> transactions);

    List<Transaction> getIncomes(List<Transaction> transactions);

    Map<Tag, BigDecimal> getAverageByTag(List<Transaction> transactions);
}
