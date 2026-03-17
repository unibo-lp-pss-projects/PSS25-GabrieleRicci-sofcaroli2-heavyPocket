package it.unibo.heavypocket.mvc.controller;

public interface StatisticsController {

    void setAverageValue(); // --> si usa una Label, quind cast a String

    void setPieChartData(); // --> lista di oggetti Piechart.data con String, double

}
