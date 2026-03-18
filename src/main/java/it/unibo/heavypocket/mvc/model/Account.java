package it.unibo.heavypocket.mvc.model;

import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDate;
import java.util.UUID;
import java.util.Set;
import java.util.Optional;

public interface Account {

    BigDecimal getTotalBalance();

    Set<Tag> getTags();

    Budget getBudget();

    Statistics getStatistics();

    Optional<Transaction> getTransactionById(UUID id);

    List<Transaction> searchByType(TransactionType type);

    List<Transaction> searchByDate(LocalDate date);

    List<Transaction> searchByTag(Tag tag);

    List<Transaction> getTransactions();

    void addTransaction(Transaction transaction);

    void editTransaction(UUID id, Transaction newTransaction);

    void deleteTransaction(Transaction transaction);
}
