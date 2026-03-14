package it.unibo.heavypocket.mvc.model;

import java.util.List;
import java.util.Set;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import it.unibo.heavypocket.mvc.model.TransactionType;

public interface Account {

    List<Transaction> getTransactions();

    Set<Tag> getTags();

    BigDecimal getTotalBalance();

    void addTransaction(Transaction transaction);

    void editTransaction(UUID id, Transaction newTransaction);

    void deleteTransaction(Transaction transaction);

    List<Transaction> searchByType(TransactionType type);

    List<Transaction> searchByDate(LocalDate date);

    List<Transaction> searchByTag(Tag tag);
}
