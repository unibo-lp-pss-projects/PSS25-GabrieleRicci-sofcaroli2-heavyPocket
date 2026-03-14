package it.unibo.heavypocket.mvc.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.FiltersData;

public interface AccountController {

        List<Transaction> getTransactions();

        void showTransactions(); // chiamato dalla view per mostrare la lista di transaction

        void showTags(); // chiamato dalla view per mostrare la lista di tag

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

        void search(FiltersData filters);
}
