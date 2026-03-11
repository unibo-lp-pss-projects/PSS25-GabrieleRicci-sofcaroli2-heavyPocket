package it.unibo.heavypocket.mvc.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public interface Statistics {

    // sotto il conto --> media totale delle uscite
    BigDecimal getAverageExpense();

    // piechart --> soldi spesi divisi per categoria: con tag invece di string per
    // passare anche il colore
    Map<Tag, BigDecimal> getExpenseByTag();

    // line chart --> balance diviso per mese alla fine del mese
    Map<LocalDate, BigDecimal> getStatisticsByMonth();
}
