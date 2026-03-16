package it.unibo.heavypocket.mvc.controller;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.List;
import java.util.Map;

import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.DTO.FiltersDTO;
import it.unibo.heavypocket.mvc.DTO.TransactionDTO;
import it.unibo.heavypocket.mvc.model.Tag;

public interface AccountController {

        void showTransactions(); // chiamato dalla view per mostrare la lista di transaction

        void showTags(); // chiamato dalla view per mostrare la lista di tag
    
        void showTotalBalance(); // chiamato dalla view per mostrare il bilancio totale

        void addTransaction(TransactionDTO transactionDTO);

        void callToEditTransaction(UUID id);

        void editTransaction(UUID id, TransactionDTO transactionDTO);

        void deleteTransaction(UUID id);

        void search(FiltersDTO filters);
}
