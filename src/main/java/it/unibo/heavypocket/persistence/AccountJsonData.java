package it.unibo.heavypocket.persistence;

import java.math.BigDecimal;
import java.util.List;

public record AccountJsonData(
        List<TransactionJsonData> transactions,
        BigDecimal balance,
        BigDecimal budget,
        BigDecimal savingTarget) {
}
