package it.unibo.heavypocket.mvc.view;

import java.util.List;
import java.util.UUID;
import java.math.BigDecimal;
import java.util.Map;

import it.unibo.heavypocket.mvc.controller.AccountController;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.DTO.TransactionDTO;

public interface AccountView {

    void setController(AccountController controller);

    AccountController getController();

    void showTransactionList(List<Transaction> transactions); // mostra la lista di transazioni

    void showTagList(List<Tag> tags); // mostra la lista di tag
    
    void showBalance(String balance);

    void showEditTransaction(UUID id, TransactionDTO transactionDTO); // mostra il dialog

    void showError(String error); // mostra gli errori

    void showAverage(String averageExpense, String averageIncome); 

    void showPieChartData(Map<Tag, BigDecimal> pieChartData);

    // void showLineChartData(Map<LocalDate, BigDecimal> data);

}
