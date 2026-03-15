package it.unibo.heavypocket.mvc.DTO;

import java.time.LocalDate;

import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.TransactionType;

public record FiltersDTO(
        LocalDate date,
        TransactionType type,
        Tag tag) {
}
