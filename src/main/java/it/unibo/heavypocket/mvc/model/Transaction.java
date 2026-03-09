package it.unibo.heavypocket.mvc.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import it.unibo.heavypocket.mvc.model.impl.TransactionBuilderImpl;

public interface Transaction {

    UUID getId();

    BigDecimal getAmount();

    BigDecimal getSignedAmount();

    LocalDate getDate();

    String getDescription();

    boolean isExpense();

    Tag getTag();

    static TransactionBuilder builder() {
        return new TransactionBuilderImpl();
    }
}
