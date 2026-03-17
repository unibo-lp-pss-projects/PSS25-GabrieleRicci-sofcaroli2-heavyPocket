package it.unibo.heavypocket.mvc.controller.validation;

import java.math.BigDecimal;

import it.unibo.heavypocket.mvc.DTO.TransactionDTO;

public final class Validation {

    private static final String ERROR_AMOUNT = "Amount must be greater than zero";
    private static final String ERROR_FIELDS = "Please fill in all fields";
    private static final String ERROR_AMOUNT_FORMAT = "Invalid amount format";

    private Validation() {
        throw new AssertionError("Utility class should not be instantiated");
    }

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

    public static BigDecimal validateAmount(final String amountString) {
        final String amount = amountString.trim().replace(',', '.');
        final BigDecimal finalAmount;
        try {
            finalAmount = new BigDecimal(amount);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_AMOUNT_FORMAT);
        }
        if (finalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(ERROR_AMOUNT);
        }
        return finalAmount;
    }
}
