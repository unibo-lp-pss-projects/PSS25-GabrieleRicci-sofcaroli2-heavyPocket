package it.unibo.heavypocket.mvc.model;

import java.time.LocalDate;

public record FiltersData(
                LocalDate date,
                TransactionType type,
                String tag) {
}
