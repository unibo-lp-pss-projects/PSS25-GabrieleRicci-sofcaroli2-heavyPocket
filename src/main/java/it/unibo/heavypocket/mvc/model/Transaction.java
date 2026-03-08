package it.unibo.heavypocket.mvc.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.TransactionBuilder;

// @TODO updatte dopo aver creato TransactionBuilderImpl
public interface Transaction {

    UUID getId();

    BigDecimal getAmount();

    BigDecimal getSignedAmount();

    LocalDate getDate();

    String getDescription();

    boolean isExpense();

    Tag getTag();

    // static TransactionBuilder builder() {
    //     return new TransactionBuilderImpl();
    // }
}
