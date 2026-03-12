package it.unibo.heavypocket.mvc.view.impl;

import java.util.List;
import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import it.unibo.heavypocket.mvc.view.AccountView;
import it.unibo.heavypocket.mvc.controller.AccountController;
import it.unibo.heavypocket.mvc.controller.impl.AccountControllerImpl;
import it.unibo.heavypocket.mvc.model.Account;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.impl.AccountImpl;
import it.unibo.heavypocket.mvc.model.impl.TagEnumImpl;
import it.unibo.heavypocket.mvc.view.panels.TransactionListPanel;

public final class AccountViewImpl extends Application implements AccountView {

    private AccountController controller;
    private TransactionListPanel transactionListPanel;
    // @TODO: implementare panel

    @Override
    public void start(final Stage primaryStage) {
        final Account model = new AccountImpl(List.of(Transaction.builder()
                .withId(UUID.randomUUID())
                .withAmount(BigDecimal.valueOf(50.0))
                .withDate(LocalDate.now())
                .withDescription("description")
                .isExpense(true)
                .withTag(TagEnumImpl.FOOD)
                .build()));

        final Label message = new Label("Hello, JavaFX!");
        message.setFont(new Font(100));
        primaryStage.setScene(new Scene(message));
        primaryStage.setTitle("HeavyPocket");

        final AccountController controller = new AccountControllerImpl(model, this);

        // transactionListPanel.setOnSearch(controller::searchByType);
        // transactionListPanel.setOnSearch(controller::searchByTag);
        // transactionListPanel.setOnSearch(controller::searchByDate);

        primaryStage.show();
    }

    @Override
    public void setController(final AccountController controller) {
        this.controller = controller;
    }

    @Override
    public AccountController getController() {
        return this.controller;
    }

    @Override
    public void showTransactionList(final List<Transaction> transactions) {
        transactionListPanel.setTransactions(transactions);
    }

    @Override
    public void showError(final String error) {
        final Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(error);
        alert.showAndWait();
    }

}
