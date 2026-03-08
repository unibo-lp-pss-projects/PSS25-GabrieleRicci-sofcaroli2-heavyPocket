package it.unibo.heavypocket.mvc.model;

import java.math.BigDecimal;
import java.time.LocalDate;

// @TODO campi mancanti
public interface Transaction {
    
    UUID getId();
    BigDecimal getAmount();
    BigDecimal getSignedAmount();
    LocalDate getDate();
    String getDescription();
    boolean isExpense();
    // Tag getTag();
}
