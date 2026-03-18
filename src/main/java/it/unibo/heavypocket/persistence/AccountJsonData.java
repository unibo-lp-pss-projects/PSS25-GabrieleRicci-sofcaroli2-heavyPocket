package it.unibo.heavypocket.persistence;

import java.math.BigDecimal;
import java.util.List;

/**
 * Account payload used for JSON persistence.
 * @param transactions serialized transactions list
 * @param balance persisted account balance
 * @param budget persisted budget limit
 */
public record AccountJsonData(
        List<TransactionJsonData> transactions,
        BigDecimal balance,
        BigDecimal budget) {
}
