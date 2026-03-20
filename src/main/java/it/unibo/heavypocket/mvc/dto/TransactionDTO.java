package it.unibo.heavypocket.mvc.dto;

import java.time.LocalDate;

import it.unibo.heavypocket.mvc.model.TransactionType;
import it.unibo.heavypocket.mvc.model.Tag;

/**
 * Record representing the dto for a transaction.
 * 
 * @param type the type of the transaction
 * @param amount the amount of the transaction
 * @param date the date of the transaction
 * @param description the description of the transaction
 * @param tag the tag of the transaction
 */
public record TransactionDTO(
        TransactionType type,
        String amount,
        LocalDate date,
        String description,
        Tag tag) {
}
