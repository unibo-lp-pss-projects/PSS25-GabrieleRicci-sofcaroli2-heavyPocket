package it.unibo.heavypocket.mvc.view.panels.impl;

import java.time.LocalDate;

public record TransactionData(
        Double amount,
        LocalDate date,
        String description,
        Boolean isExpense,
        String tag) {
}
