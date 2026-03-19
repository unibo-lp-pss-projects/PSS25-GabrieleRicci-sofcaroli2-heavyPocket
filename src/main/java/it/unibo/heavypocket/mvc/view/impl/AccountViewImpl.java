package it.unibo.heavypocket.mvc.view.impl;

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

import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.controller.AccountController;
import it.unibo.heavypocket.mvc.view.AccountView;
import it.unibo.heavypocket.mvc.view.panels.TransactionListPanel;
import it.unibo.heavypocket.mvc.view.panels.StatisticsBalancePanel;
import it.unibo.heavypocket.mvc.view.panels.AddTransactionPanel;
import it.unibo.heavypocket.mvc.view.panels.BudgetPanel;
import it.unibo.heavypocket.mvc.view.panels.GraphsPanel;
import it.unibo.heavypocket.mvc.DTO.TransactionDTO;

public final class AccountViewImpl implements AccountView {

    private AccountController controller;
    private TransactionListPanel transactionListPanel;
    private StatisticsBalancePanel statisticsBalancePanel;
    private AddTransactionPanel addTransactionPanel;
    private BudgetPanel budgetPanel;
    private GraphsPanel graphsPanel;

    public AccountViewImpl(
            final TransactionListPanel transactionListPanel,
            final StatisticsBalancePanel statisticsBalancePanel,
            final AddTransactionPanel addTransactionPanel,
            final BudgetPanel budgetPanel,
            final GraphsPanel graphsPanel) {
        this.transactionListPanel = transactionListPanel;
        this.statisticsBalancePanel = statisticsBalancePanel;
        this.addTransactionPanel = addTransactionPanel;
        this.budgetPanel = budgetPanel;
        this.graphsPanel = graphsPanel;
    }

    @Override
    public void start(final Stage primaryStage) {
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
        return controller;
    }

    @Override
    public void showBalance(final String balance) {
        statisticsBalancePanel.setBalance(balance);
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
    public void showBudgetElements(final String limit, final String spent) {
        budgetPanel.setBudgetElements(limit, spent);
    }

    @Override
    public void showLimitExceeded(final boolean isExceeded) {
        budgetPanel.showLimitExceeded(isExceeded);
    }

    @Override
    public void showAverage(final String expenses, final String incomes) {
        statisticsBalancePanel.setAverageValue(expenses, incomes);
    }

    @Override
    public void showPieChartData(final Map<Tag, BigDecimal> expensesByTag, final Map<Tag, BigDecimal> incomesByTag) {
        final ObservableList<PieChart.Data> pieChartExpense = expensesByTag.entrySet().stream()
                .map(entry -> new PieChart.Data(
                        entry.getKey().getName(),
                        entry.getValue().doubleValue()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        final ObservableList<PieChart.Data> pieChartIncome = incomesByTag.entrySet().stream()
                .map(entry -> new PieChart.Data(
                        entry.getKey().getName(),
                        entry.getValue().doubleValue()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        graphsPanel.setPieChartData(pieChartExpense, pieChartIncome);
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
