package it.unibo.heavypocket.persistence;

import java.math.BigDecimal;
import java.util.List;

import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.TransactionType;
import it.unibo.heavypocket.persistence.impl.TransactionJsonData;

public record AccountJsonData(
    List<TransactionJsonData> transactions,
    BigDecimal balance,
    BigDecimal budget,
    BigDecimal savingTarget) {
}
