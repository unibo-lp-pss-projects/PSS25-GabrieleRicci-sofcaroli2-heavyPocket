package it.unibo.heavypocket.persistence;

import java.math.BigDecimal;
import java.util.List;

//@TODO: nel json dovrebbero andarci tutte le cose che non vengono calcolate da sole, 
// quindi balance non dovrebbe venire salvato in quanto calcolato da AccountImpl
/**
 * Account payload used for JSON persistence.
 * 
 * @param transactions serialized transactions list
 * @param balance      persisted account balance
 * @param budget       persisted budget limit
 */
public record AccountJsonData(
                List<TransactionJsonData> transactions,
                BigDecimal budget) {
}
