package it.unibo.heavypocket.mvc.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.Optional;

public interface Account {

    List<Transaction> getTransactions();

    Set<Tag> getTags();

    BigDecimal getTotalBalance();

    BigDecimal getBudgetLimit();

    void addTransaction(Transaction transaction);

    void editTransaction(UUID id, Transaction newTransaction);

    void deleteTransaction(Transaction transaction);

    List<Transaction> searchByType(TransactionType type);

    List<Transaction> searchByDate(LocalDate date);

    List<Transaction> searchByTag(Tag tag);

    Optional<Transaction> getTransactionById(UUID id);
}
