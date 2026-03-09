package it.unibo.heavypocket.mvc.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface TransactionBuilder {

    TransactionBuilder withId(UUID id);

    TransactionBuilder withAmount(BigDecimal amount);

    TransactionBuilder withDate(LocalDate date);

    TransactionBuilder withDescription(String description);

    TransactionBuilder IsExpense(boolean isExpense);

    TransactionBuilder withTag(Tag tag);

    Transaction build();
}
