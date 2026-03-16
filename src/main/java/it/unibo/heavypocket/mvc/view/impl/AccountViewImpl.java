package it.unibo.heavypocket.mvc.view.impl;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.util.List;
import java.util.UUID;

import it.unibo.heavypocket.mvc.view.AccountView;
import it.unibo.heavypocket.mvc.controller.AccountController;
import it.unibo.heavypocket.mvc.controller.impl.AccountControllerImpl;
import it.unibo.heavypocket.mvc.model.Account;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.view.panels.AddTransactionPanel;
import it.unibo.heavypocket.mvc.view.panels.TransactionListPanel;
import it.unibo.heavypocket.mvc.view.panels.impl.AddTransactionPanelImpl;
import it.unibo.heavypocket.mvc.view.panels.impl.TransactionListPanelImpl;
import it.unibo.heavypocket.mvc.DTO.TransactionDTO;
import it.unibo.heavypocket.persistence.HeavyPocketLoader;

public final class AccountViewImpl extends Application implements AccountView {

    private AccountController controller;
    private TransactionListPanel transactionListPanel;
    private AddTransactionPanel addTransactionPanel;

    // @TODO: implementare panel
    // @TODO implementare il loader
    @Override
    public void start(final Stage primaryStage) {
        final Account model = HeavyPocketLoader.loadData();

        // inizializzazione pannelli
        this.transactionListPanel = new TransactionListPanelImpl();
        this.addTransactionPanel = new AddTransactionPanelImpl();
        final VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(transactionListPanel.getRoot(), addTransactionPanel.getRoot());
        final Scene scene = new Scene(root, 800, 600);

        this.controller = new AccountControllerImpl(model, this);
        transactionListPanel.setOnSearch(controller::search);
        transactionListPanel.setOnDelete(controller::deleteTransaction);
        transactionListPanel.setOnEdit(controller::callToEditTransaction);
        addTransactionPanel.setOnAdd(controller::addTransaction);
        addTransactionPanel.setOnEdit(controller::editTransaction);

        primaryStage.setTitle("HeavyPocket");
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
        addTransactionPanel.setTagList(tags);
    }

    @Override
    public void showEditTransaction(final UUID id, final TransactionDTO transactionDTO) {
        addTransactionPanel.editTransaction(id, transactionDTO);
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
