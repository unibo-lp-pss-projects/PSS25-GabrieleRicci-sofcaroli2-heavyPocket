package it.unibo.heavypocket.persistence;

import java.math.BigDecimal;

import it.unibo.heavypocket.mvc.model.TransactionType;

/**
 * Transaction payload used for JSON persistence.
 * 
 * @param id          transaction identifier
 * @param type        transaction type
 * @param amount      transaction amount
 * @param date        transaction date (in ISO-8601 format)
 * @param description transaction description
 * @param tag         transaction tag name
 */
public record TransactionJsonData(
                String id,
                TransactionType type,
                BigDecimal amount,
                String date,
                String description,
                String tag) {
}
