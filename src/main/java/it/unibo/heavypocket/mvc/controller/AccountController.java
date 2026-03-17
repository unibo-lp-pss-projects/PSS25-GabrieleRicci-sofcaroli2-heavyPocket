package it.unibo.heavypocket.mvc.controller;

import java.util.UUID;

import it.unibo.heavypocket.mvc.DTO.FiltersDTO;
import it.unibo.heavypocket.mvc.DTO.TransactionDTO;

//@TODO capire se ha senso avere le altre interfacce se alla fine hanno 2/3 metodi l'una
public interface AccountController {

        void showTransactions();

        void showTags();

        void showTotalBalance();

        void showBudgetElements();

        void addTransaction(TransactionDTO transactionDTO);

        void callToEditTransaction(UUID id);

        void editTransaction(UUID id, TransactionDTO transactionDTO);

        void deleteTransaction(UUID id);

        void search(FiltersDTO filters);

        void updateBudgetLimit(String newLimit);
}
