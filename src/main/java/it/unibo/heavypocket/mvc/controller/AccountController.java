package it.unibo.heavypocket.mvc.controller;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.List;

import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.DTO.FiltersDTO;
import it.unibo.heavypocket.mvc.DTO.TransactionDTO;

public interface AccountController {

        void showTransactions(); // chiamato dalla view per mostrare la lista di transaction

        void showTags(); // chiamato dalla view per mostrare la lista di tag
    
        void showTotalBalance();

        void addTransaction(TransactionDTO transactionDTO);

        void callToEditTransaction(UUID id);

        void editTransaction(UUID id, TransactionDTO transactionDTO);

        void deleteTransaction(UUID id);

        void search(FiltersDTO filters);
}
