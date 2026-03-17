package it.unibo.heavypocket.mvc.view;

import javafx.stage.Stage;

import java.util.List;
import java.util.UUID;
import java.math.BigDecimal;
import java.util.Map;

import it.unibo.heavypocket.mvc.controller.AccountController;
import it.unibo.heavypocket.mvc.DTO.TransactionDTO;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.Tag;

public interface AccountView {

    void start(Stage primaryStage);

    void setController(AccountController controller);

    AccountController getController();

    void showTransactionList(List<Transaction> transactions);

    void showTagList(List<Tag> tags);

    void showBalance(String balance);

    void showEditTransaction(UUID id, TransactionDTO transactionDTO);

    void showError(String error);

    void showAverage(String averageExpense, String averageIncome);

    void showPieChartData(Map<Tag, BigDecimal> pieChartExpense, Map<Tag, BigDecimal> pieChartIncome);

    void showBudgetElements(String limit, String spent);

    void showLimitExceeded(boolean isExceeded);
}
