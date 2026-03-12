package it.unibo.heavypocket.mvc.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.Tag;

public interface AccountController {

    List<Transaction> getTransactions();

    BigDecimal getTotalBalance();

    void addTransaction(BigDecimal amount,
            LocalDate date,
            String description,
            boolean expense,
            Tag tag);

    void editTransaction(
            UUID id,
            BigDecimal amount,
            LocalDate date,
            String description,
            boolean expense,
            Tag tag);

    void deleteTransaction(Transaction transaction);

    List<Transaction> searchByType(boolean expense);

    List<Transaction> searchByDate(LocalDate date);

    List<Transaction> searchByTag(Tag tag);
}
