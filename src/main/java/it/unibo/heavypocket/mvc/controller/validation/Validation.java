package it.unibo.heavypocket.mvc.controller.validation;

import java.math.BigDecimal;
import java.math.RoundingMode;

import it.unibo.heavypocket.mvc.dto.TransactionDTO;

/**
 * Utility class for Validation.
 */
public final class Validation {

    private static final String ERROR_AMOUNT = "Amount must be greater than zero";
    private static final String ERROR_FIELDS = "Please fill in all fields";
    private static final String ERROR_AMOUNT_FORMAT = "Invalid amount format";

    /**
     * Private constructor of the class Validation.
     */
    private Validation() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    /**
     * Checks if all required fields in a TransactionDTO are present and not empty.
     * 
     * @param transactionDTO the transaction data to check.
     * @throws IllegalArgumentException if any field is null or blank.
     */
    public static void validateTransactionDTO(final TransactionDTO transactionDTO) {
        if (transactionDTO == null
                || transactionDTO.type() == null
                || transactionDTO.amount() == null
                || transactionDTO.amount().isBlank()
                || transactionDTO.date() == null
                || transactionDTO.description() == null
                || transactionDTO.description().isBlank()
                || transactionDTO.tag() == null) {
            throw new IllegalArgumentException(ERROR_FIELDS);
        }
    }

    /**
     * Converts a string to a BigDecimal and checks if it is a positive value.
     * 
     * @param amountString the string to convert and validate.
     * @return the validated amount.
     * @throws IllegalArgumentException if the format is invalid or the value is
     *                                  0/negative.
     */
    public static BigDecimal validateAmount(final String amountString) {
        if (amountString == null || amountString.isBlank()) {
            throw new IllegalArgumentException(ERROR_FIELDS);
        }
        final String amount = amountString.trim().replace(',', '.');
        final BigDecimal finalAmount;
        try {
            finalAmount = new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_AMOUNT_FORMAT, e);
        }
        if (finalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(ERROR_AMOUNT);
        }
        return finalAmount;
    }
}
