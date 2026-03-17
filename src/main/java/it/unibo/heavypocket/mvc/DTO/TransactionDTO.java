package it.unibo.heavypocket.mvc.DTO;

import java.time.LocalDate;

import it.unibo.heavypocket.mvc.model.TransactionType;
import it.unibo.heavypocket.mvc.model.Tag;

public record TransactionDTO(
        TransactionType type,
        String amount,
        LocalDate date,
        String description,
        Tag tag) {
}
