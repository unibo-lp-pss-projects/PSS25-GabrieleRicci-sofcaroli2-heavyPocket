package it.unibo.heavypocket.mvc.view.impl;

import java.util.List;
import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.scene.Scene;
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

public class AccountViewImpl extends Application implements AccountView {

    private AccountController controller;
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

        primaryStage.show();
    }

    @Override
    public void setController(AccountController controller) {
        this.controller = controller;
    }

    @Override
    public AccountController getController() {
        return this.controller;
    }

    @Override
    public void showTransactionList(List<Transaction> transactions) {
        // Implementazione per mostrare la lista di transazioni
        // Ad esempio, potresti stampare le transazioni sulla console o utilizzare
        // un'interfaccia grafica
    }

    @Override
    public void showError(String error) {
        // Implementazione per mostrare gli errori
        // Ad esempio, potresti stampare l'errore sulla console o utilizzare
        // un'interfaccia grafica
    }

}