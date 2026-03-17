package it.unibo.heavypocket.mvc.DTO;

import java.time.LocalDate;

import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.TransactionType;

public record FiltersDTO(
        TransactionType type,
        LocalDate date,
        Tag tag) {
}
