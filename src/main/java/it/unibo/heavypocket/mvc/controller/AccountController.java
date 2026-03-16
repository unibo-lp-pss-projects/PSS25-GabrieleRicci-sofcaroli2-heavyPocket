package it.unibo.heavypocket.mvc.controller;

import java.util.UUID;
import java.math.BigDecimal;

import it.unibo.heavypocket.mvc.DTO.FiltersDTO;
import it.unibo.heavypocket.mvc.DTO.TransactionDTO;

public interface AccountController {

        void showTransactions();

        void showTags();

        void showTotalBalance();

        void addTransaction(TransactionDTO transactionDTO);

        void callToEditTransaction(UUID id);

        void editTransaction(UUID id, TransactionDTO transactionDTO);

        void deleteTransaction(UUID id);

        void updateBudgetLimit(BigDecimal newLimit);

        void search(FiltersDTO filters);
}
