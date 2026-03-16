package it.unibo.heavypocket.mvc.view.impl;

import java.math.BigDecimal;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import it.unibo.heavypocket.mvc.view.AccountView;
import it.unibo.heavypocket.mvc.controller.AccountController;
import it.unibo.heavypocket.mvc.controller.BudgetController;
import it.unibo.heavypocket.mvc.controller.impl.AccountControllerImpl;
import it.unibo.heavypocket.mvc.controller.impl.BudgetControllerImpl;
import it.unibo.heavypocket.mvc.model.Account;
import it.unibo.heavypocket.mvc.model.TransactionType;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.view.panels.AddTransactionPanel;
import it.unibo.heavypocket.mvc.view.panels.BudgetPanel;
import it.unibo.heavypocket.mvc.view.panels.TransactionListPanel;
import it.unibo.heavypocket.mvc.view.panels.impl.BudgetPanelImpl;
import it.unibo.heavypocket.mvc.view.panels.impl.AddTransactionPanelImpl;
import it.unibo.heavypocket.mvc.view.panels.impl.TransactionListPanelImpl;
import it.unibo.heavypocket.mvc.model.impl.BudgetImpl;
import it.unibo.heavypocket.persistence.HeavyPocketLoader;

public final class AccountViewImpl extends Application implements AccountView {

    private AccountController controller;
    private BudgetController budgetController;
    private TransactionListPanel transactionListPanel;
    private AddTransactionPanel addTransactionListPanel;
    private BudgetPanel budgetPanel;

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
        this.budgetPanel = new BudgetPanelImpl();
        final VBox root = new VBox();
        root.getChildren().addAll(transactionListPanel.getRoot(), budgetPanel.getRoot(), addTransactionListPanel.getRoot());
        final Scene scene = new Scene(root, 800, 600);

        this.budgetController = new BudgetControllerImpl(new BudgetImpl(getValidBudgetLimit(model.getBudgetLimit())));
        this.controller = new AccountControllerImpl(model, this);

        transactionListPanel.setOnSearch(controller::search);
        transactionListPanel.setOnDelete(controller::deleteTransaction);
        budgetPanel.setOnUpdateLimit(this::updateBudgetLimit);

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
        refreshBudgetCurrentSpent();
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

    private void initializeCurrentSpentFromTransactions(final List<Transaction> transactions) {
        for (final Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.EXPENSE) {
                this.budgetController.addExpense(transaction.getAmount());
            }
        }
    }

    private void updateBudgetLimit(final BigDecimal newLimit) {
        try {
            this.budgetController.updateBudgetLimit(newLimit);
            updateBudgetPanelStatus();
        } catch (final IllegalArgumentException ex) {
            showError(ex.getMessage());
        }
    }

    private void updateBudgetPanelStatus() {
        this.budgetPanel.setBudgetStatus(
                this.budgetController.getBudgetLimit(),
                this.budgetController.getCurrentSpent(),
                this.budgetController.isBudgetExceeded());
    }

    private void refreshBudgetCurrentSpent() {
        final BigDecimal budgetLimit = this.budgetController.getBudgetLimit();
        this.budgetController = new BudgetControllerImpl(new BudgetImpl(budgetLimit));
        initializeCurrentSpentFromTransactions(this.controller.getTransactions());
        updateBudgetPanelStatus();
    }

    private BigDecimal getValidBudgetLimit(final BigDecimal budgetLimit) {
        if (budgetLimit != null && budgetLimit.compareTo(BigDecimal.ZERO) > 0) {
            return budgetLimit;
        }
        return BigDecimal.ONE;
    }
}
