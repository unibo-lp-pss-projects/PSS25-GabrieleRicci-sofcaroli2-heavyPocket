package it.unibo.heavypocket.mvc.model;

import java.math.BigDecimal;

/**
 * Interface for reading and updating the account budget limit.
 */
public interface Budget {

    /**
     * Gets the current budget limit.
     * 
     * @return the current budget limit
     */
    BigDecimal getLimit();

    /**
     * Sets the budget limit.
     * 
     * @param newLimit the new budget limit to set
     * @throws IllegalArgumentException if newLimit is less than or equal to zero
     * @throws NullPointerException     if newLimit is null
     */
    void setLimit(BigDecimal newLimit);
}
