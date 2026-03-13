package it.unibo.heavypocket.mvc.view.impl;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import it.unibo.heavypocket.mvc.view.AccountView;
import it.unibo.heavypocket.mvc.controller.AccountController;
import it.unibo.heavypocket.mvc.controller.impl.AccountControllerImpl;
import it.unibo.heavypocket.mvc.model.Account;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.impl.AccountImpl;
import it.unibo.heavypocket.mvc.model.impl.TagEnumImpl;
import it.unibo.heavypocket.mvc.view.panels.AddTransactionPanel;
import it.unibo.heavypocket.mvc.view.panels.TransactionListPanel;
import it.unibo.heavypocket.mvc.view.panels.impl.AddTransactionPanelImpl;
import it.unibo.heavypocket.mvc.view.panels.impl.TransactionListPanelImpl;
import it.unibo.heavypocket.persistence.HeavyPocketLoader;

public final class AccountViewImpl extends Application implements AccountView {

    private AccountController controller;
    private TransactionListPanel transactionListPanel;
    private AddTransactionPanel addTransactionListPanel;

    // @TODO: implementare panel
    // @TODO implementare il loader
    @Override
    public void start(final Stage primaryStage) {
        // final List<Transaction> transactions = List.of(Transaction.builder()
        //         .withId(UUID.randomUUID())
        //         .withAmount(BigDecimal.valueOf(50.0))
        //         .withDate(LocalDate.now())
        //         .withDescription("description")
        //         .isExpense(true)
        //         .withTag(TagEnumImpl.FOOD)
        //         .build(),
        //         Transaction.builder()
        //                 .withId(UUID.randomUUID())
        //                 .withAmount(BigDecimal.valueOf(150.0))
        //                 .withDate(LocalDate.now())
        //                 .withDescription("description 2")
        //                 .isExpense(false)
        //                 .withTag(TagEnumImpl.SALARY)
        //                 .build());
        // final Set<Tag> tags = Set.of(TagEnumImpl.FOOD, TagEnumImpl.ENTERTAINMENT, TagEnumImpl.SALARY);
        

        // final Account model = new AccountImpl(transactions, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, tags);
        final Account model = HeavyPocketLoader.loadData();

        // inizializzazione pannelli
        this.transactionListPanel = new TransactionListPanelImpl();
        this.addTransactionListPanel = new AddTransactionPanelImpl();

        // final Label message = new Label("Hello, JavaFX!");
        // message.setFont(new Font(100));
        // primaryStage.setScene(new Scene(message));
        primaryStage.setTitle("HeavyPocket");

        this.controller = new AccountControllerImpl(model, this);

        // transactionListPanel.setOnSearch(controller::searchByType);
        // transactionListPanel.setOnSearch(controller::searchByTag);
        // transactionListPanel.setOnSearch(controller::searchByDate);

        final VBox root = new VBox();
        root.getChildren().addAll(transactionListPanel.getRoot(), addTransactionListPanel.getRoot());
        final Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);

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
    public void showTagList(final List<Tag> tags) {
        transactionListPanel.setTagList(tags);
        addTransactionListPanel.setTagList(tags);
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
