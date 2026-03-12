package it.unibo.heavypocket.mvc.view;

import java.util.List;

import it.unibo.heavypocket.mvc.controller.AccountController;

public interface AccountView {

    void setController(AccountController controller);

    AccountController getController();

    void showTransactionList(List<Transaction> transactions); // mostra la lista di transazioni

    void showError(String error); // mostra gli errori
}
