package it.unibo.heavypocket.mvc.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.List;

public interface Statistics {

    // sotto il conto --> media totale delle uscite
    BigDecimal getAverageExpense(List<Transaction> expenses);

    // piechart --> soldi spesi divisi per categoria: con tag invece di string per
    // passare anche il colore
    Map<Tag, BigDecimal> getExpenseByTag(List<Transaction> expenses);

    // line chart --> balance diviso per mese alla fine del mese
    Map<LocalDate, BigDecimal> getStatisticsByMonth(List<Transaction> transactions);
}
