package it.unibo.heavypocket;

import javafx.stage.Stage;

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

import it.unibo.heavypocket.mvc.model.Account;
import it.unibo.heavypocket.mvc.model.Statistics;
import it.unibo.heavypocket.mvc.model.impl.StatisticsImpl;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.controller.AccountController;
import it.unibo.heavypocket.mvc.controller.impl.AccountControllerImpl;
import it.unibo.heavypocket.mvc.view.AccountView;
import it.unibo.heavypocket.mvc.view.panels.TransactionListPanel;
import it.unibo.heavypocket.mvc.view.panels.StatisticsBalancePanel;
import it.unibo.heavypocket.mvc.view.panels.AddTransactionPanel;
import it.unibo.heavypocket.mvc.view.panels.BudgetPanel;
import it.unibo.heavypocket.mvc.view.panels.GraphsPanel;
import it.unibo.heavypocket.mvc.view.panels.impl.TransactionListPanelImpl;
import it.unibo.heavypocket.mvc.view.panels.impl.StatisticsBalancePanelImpl;
import it.unibo.heavypocket.mvc.view.panels.impl.AddTransactionPanelImpl;
import it.unibo.heavypocket.mvc.view.panels.impl.BudgetPanelImpl;
import it.unibo.heavypocket.mvc.view.panels.impl.GraphsPanelImpl;
import it.unibo.heavypocket.persistence.Saver;
import it.unibo.heavypocket.persistence.impl.SaverImpl;
import it.unibo.heavypocket.persistence.impl.Loader;
import it.unibo.heavypocket.mvc.view.impl.AccountViewImpl;

public final class HeavyPocketApp {

    public static void main(final String... args) {
        Application.launch(HeavyPocketFxApp.class, args);
    }

    public static final class HeavyPocketFxApp extends Application {
        @Override
        public void start(final Stage primaryStage) {
            final Account model = Loader.loadData();
            final Statistics statistics = new StatisticsImpl();
            final Saver saver = new SaverImpl();
            final TransactionListPanel transactionListPanel = new TransactionListPanelImpl();
            final StatisticsBalancePanel statisticsBalancePanel = new StatisticsBalancePanelImpl();
            final AddTransactionPanel addTransactionPanel = new AddTransactionPanelImpl();
            final BudgetPanel budgetPanel = new BudgetPanelImpl();
            final GraphsPanel graphsPanel = new GraphsPanelImpl();
            final AccountView view = new AccountViewImpl(
                    transactionListPanel,
                    statisticsBalancePanel,
                    addTransactionPanel,
                    budgetPanel,
                    graphsPanel);
            final AccountController controller = new AccountControllerImpl(model, view, statistics, saver);
            view.start(primaryStage);
        }
    }
}
