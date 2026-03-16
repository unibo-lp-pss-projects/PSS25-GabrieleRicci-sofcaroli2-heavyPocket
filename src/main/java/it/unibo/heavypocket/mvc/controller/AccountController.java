package it.unibo.heavypocket.mvc.controller;

import java.util.UUID;

import it.unibo.heavypocket.mvc.DTO.FiltersDTO;
import it.unibo.heavypocket.mvc.DTO.TransactionDTO;

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
