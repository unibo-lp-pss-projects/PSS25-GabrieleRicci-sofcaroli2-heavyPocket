package it.unibo.heavypocket.mvc.DTO;

import java.time.LocalDate;

import it.unibo.heavypocket.mvc.model.TransactionType;
import it.unibo.heavypocket.mvc.model.Tag;

public record TransactionDTO(
        String amount,
        LocalDate date,
        String description,
        TransactionType type,
        Tag tag) {
}
