package it.unibo.heavypocket.mvc.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import it.unibo.heavypocket.mvc.model.Tag;

public interface StatisticsController {

    void setAverageValue(); // --> si usa una Label, quind cast a String

    // Map<Tag, BigDecimal> getPieChartData(); // --> lista di oggetti Piechart.data con String, double

    // Map<LocalDate, BigDecimal> getLineChartData();
    // // XYChart.Data<X,Y> sono i dati: String asse Z e Double asse X, BigDecimal asse
    // // Y XYChart.Series<X,Y>

}
