package it.unibo.heavypocket.persistence.impl;

import java.math.BigDecimal;
import java.util.List;

import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.TransactionType;

public record TransactionJsonData(
    String id,
    BigDecimal amount,
    String date,
    String description,
    TransactionType type,
    String tag) {
}
