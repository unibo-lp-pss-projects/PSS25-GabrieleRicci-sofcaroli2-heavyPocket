package it.unibo.heavypocket.mvc.model;

import java.math.BigDecimal;

public interface Budget {

    BigDecimal getLimit();

    void setLimit(BigDecimal newLimit);
    
    // BigDecimal getCurrentSpent();

    // void addExpense(BigDecimal amount);

    // boolean isExceeded();
}
