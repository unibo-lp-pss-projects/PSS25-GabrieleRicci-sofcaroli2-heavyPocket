package it.unibo.heavypocket.mvc.view.impl;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Map;

import it.unibo.heavypocket.mvc.view.AccountView;
import it.unibo.heavypocket.mvc.controller.AccountController;
import it.unibo.heavypocket.mvc.controller.impl.AccountControllerImpl;
import it.unibo.heavypocket.mvc.model.Account;
import it.unibo.heavypocket.mvc.model.Statistics;
import it.unibo.heavypocket.mvc.model.impl.StatisticsImpl;
import it.unibo.heavypocket.mvc.view.panels.StatisticsBalancePanel;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.view.panels.AddTransactionPanel;
import it.unibo.heavypocket.mvc.view.panels.BudgetPanel;
import it.unibo.heavypocket.mvc.view.panels.TransactionListPanel;
import it.unibo.heavypocket.mvc.view.panels.impl.BudgetPanelImpl;
import it.unibo.heavypocket.mvc.view.panels.impl.AddTransactionPanelImpl;
import it.unibo.heavypocket.mvc.view.panels.impl.StatisticsBalancePanelImpl;
import it.unibo.heavypocket.mvc.view.panels.impl.TransactionListPanelImpl;
import it.unibo.heavypocket.mvc.view.panels.GraphsPanel;
import it.unibo.heavypocket.mvc.view.panels.impl.GraphsPanelImpl;
import it.unibo.heavypocket.mvc.DTO.TransactionDTO;
import it.unibo.heavypocket.persistence.HeavyPocketLoader;
import it.unibo.heavypocket.persistence.Saver;
import it.unibo.heavypocket.persistence.impl.SaverImpl;

public final class AccountViewImpl extends Application implements AccountView {

    private AccountController controller;
    private TransactionListPanel transactionListPanel;
    private AddTransactionPanel addTransactionPanel;
    private StatisticsBalancePanel statisticsBalancePanel;
    private GraphsPanel graphsPanel;
    private BudgetPanel budgetPanel;

    @Override
    public void start(final Stage primaryStage) {
        final Account model = HeavyPocketLoader.loadData();
        final Statistics statistics = new StatisticsImpl();
        final Saver saver = new SaverImpl();

        // inizializzazione pannelli
        this.transactionListPanel = new TransactionListPanelImpl();
        this.addTransactionPanel = new AddTransactionPanelImpl();
        this.statisticsBalancePanel = new StatisticsBalancePanelImpl();
        this.graphsPanel = new GraphsPanelImpl();
        this.budgetPanel = new BudgetPanelImpl();

        final VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(
                statisticsBalancePanel.getRoot(),
                transactionListPanel.getRoot(),
                budgetPanel.getRoot(),
                addTransactionPanel.getRoot(),
                graphsPanel.getRoot());

        this.controller = new AccountControllerImpl(model, this, statistics, saver);

        transactionListPanel.setOnSearch(controller::search);
        transactionListPanel.setOnDelete(controller::deleteTransaction);
        transactionListPanel.setOnEdit(controller::callToEditTransaction);
        addTransactionPanel.setOnAdd(controller::addTransaction);
        addTransactionPanel.setOnEdit(controller::editTransaction);
        budgetPanel.setOnUpdateLimit(controller::updateBudgetLimit);

        final Scene scene = new Scene(root, 800, 600);
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
    public void showBalance(final String balance) {
        statisticsBalancePanel.setBalance(balance);
    }

    @Override
    public void showAverage(final String expenses, final String incomes) {
        statisticsBalancePanel.setAverageValue(expenses, incomes);
    }

    @Override
    public void showPieChartData(final Map<Tag, BigDecimal> expenseByTag) {
        final ObservableList<PieChart.Data> pieChartData = expenseByTag.entrySet().stream()
                .map(entry -> new PieChart.Data(
                        entry.getKey().getName(),
                        entry.getValue().doubleValue()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        graphsPanel.setPieChartData(pieChartData);
    }

    @Override
    public void showBudgetElements(final String limit, final String spent) {
        budgetPanel.setBudgetElements(limit, spent);
    }

    @Override
    public void showLimitExceeded() {
        budgetPanel.showLimitExceeded();
    }

    @Override
    public void showLimitNotExceeded() {
        budgetPanel.showLimitNotExceeded();
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
