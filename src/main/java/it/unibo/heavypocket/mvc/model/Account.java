package it.unibo.heavypocket.mvc.model;

import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Transaction;

public interface Account {
    
    List<Transaction> getTransactions();

    BigDecimal getTotalBalance();

    void addTransaction(Transaction transaction);

    void editTransaction(UUID id, Transaction transaction);

    void deleteTransaction(UUID id);

    List<Transaction> searchByType(boolean expense);

    List<Transaction> searchByDate(LocalDate date);

    List<Transaction> searchByTag(Tag tag);
}
