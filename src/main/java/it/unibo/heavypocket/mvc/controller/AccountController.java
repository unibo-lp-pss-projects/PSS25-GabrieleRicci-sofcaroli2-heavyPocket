package it.unibo.heavypocket.mvc.controller;

import java.util.UUID;

import it.unibo.heavypocket.mvc.DTO.FiltersDTO;
import it.unibo.heavypocket.mvc.DTO.TransactionDTO;

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

        void isBudgetExceeded();

        void setAverageValue();

        void setPieChartData();
}
