package it.unibo.heavypocket.mvc.DTO;

import java.time.LocalDate;

public record TransactionDTO(
        Double amount,
        LocalDate date,
        String description,
        Boolean isExpense,
        String tag) {
}
