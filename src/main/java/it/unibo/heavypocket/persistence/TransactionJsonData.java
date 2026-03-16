package it.unibo.heavypocket.persistence;

import java.math.BigDecimal;

import it.unibo.heavypocket.mvc.model.TransactionType;

public record TransactionJsonData(
        String id,
        BigDecimal amount,
        String date,
        String description,
        TransactionType type,
        String tag) {
}
