package it.unibo.heavypocket.mvc.model;

import java.math.BigDecimal;

public interface Budget {

    BigDecimal getLimit();

    BigDecimal getCurrentSpent();

    void addExpense(BigDecimal amount);

    void setLimit(BigDecimal newLimit);

    boolean isExceeded();

}
