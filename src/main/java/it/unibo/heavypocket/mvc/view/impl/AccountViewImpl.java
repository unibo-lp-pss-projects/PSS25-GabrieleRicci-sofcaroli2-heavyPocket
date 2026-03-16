package it.unibo.heavypocket.mvc.view.impl;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

import it.unibo.heavypocket.mvc.view.AccountView;
import it.unibo.heavypocket.mvc.controller.AccountController;
import it.unibo.heavypocket.mvc.controller.impl.AccountControllerImpl;
import it.unibo.heavypocket.mvc.model.Account;
import it.unibo.heavypocket.mvc.model.Statistics;
import it.unibo.heavypocket.mvc.model.impl.StatisticsImpl;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.view.panels.AddTransactionPanel;
import it.unibo.heavypocket.mvc.view.panels.StatisticsBalancePanel;
import it.unibo.heavypocket.mvc.view.panels.TransactionListPanel;
import it.unibo.heavypocket.mvc.view.panels.impl.AddTransactionPanelImpl;
import it.unibo.heavypocket.mvc.view.panels.impl.StatisticsBalancePanelImpl;
import it.unibo.heavypocket.mvc.view.panels.impl.TransactionListPanelImpl;
import it.unibo.heavypocket.persistence.HeavyPocketLoader;

public final class AccountViewImpl extends Application implements AccountView {

    private AccountController controller;
    private TransactionListPanel transactionListPanel;
    private AddTransactionPanel addTransactionListPanel;
    private StatisticsBalancePanel statisticsBalancePanel;

    @Override
    public void start(final Stage primaryStage) {
        final Account model = HeavyPocketLoader.loadData();

        // inizializzazione pannelli
        this.transactionListPanel = new TransactionListPanelImpl();
        this.addTransactionListPanel = new AddTransactionPanelImpl();
        this.statisticsBalancePanel = new StatisticsBalancePanelImpl();

        primaryStage.setTitle("HeavyPocket");
        this.controller = new AccountControllerImpl(model, this);
        final VBox root = new VBox();
        root.getChildren().addAll(
                statisticsBalancePanel.getRoot(),
                transactionListPanel.getRoot(),
                addTransactionListPanel.getRoot());
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
    public void showBalance(final String balance) {
        statisticsBalancePanel.setBalance(balance);
    }

    @Override
    public void showError(final String error) {
        final Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(error);
        alert.showAndWait();
    }

    @Override
    public void showAverage(final String expenses, final String incomes) {
        statisticsBalancePanel.setAverageValue(expenses, incomes);
    }
}
