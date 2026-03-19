package it.unibo.heavypocket.mvc.dto;

import java.time.LocalDate;

import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.TransactionType;

/**
 * record representing the dto for the filters of the transactions.
 * 
 * @param type the type of the transaction
 * @param date the date of the transaction
 * @param tag the tag of the transaction
 */
public record FiltersDTO(
        TransactionType type,
        LocalDate date,
        Tag tag) {
}
