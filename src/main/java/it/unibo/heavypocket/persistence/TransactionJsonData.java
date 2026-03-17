package it.unibo.heavypocket.persistence;

import java.math.BigDecimal;

import it.unibo.heavypocket.mvc.model.TransactionType;

public record TransactionJsonData(
        String id,
        TransactionType type,
        BigDecimal amount,
        String date,
        String description,
        String tag) {
}
