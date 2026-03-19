package it.unibo.heavypocket;

import javafx.stage.Stage;

import javafx.application.Application;

import it.unibo.heavypocket.mvc.model.Account;
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

/**
 * Main class of the application. It launches the JavaFX application.
 */
public final class HeavyPocketApp {

    private HeavyPocketApp() {
    }

    /**
     * Main method of the application. It launches the JavaFX application.
     * 
     * @param args the command line arguments
     */
    public static void main(final String... args) {
        Application.launch(HeavyPocketFxApp.class, args);
    }

    /**
     * Inner class representing the JavaFX application. It initializes the model,
     * the view and the controller, and starts the application.
     */
    public static final class HeavyPocketFxApp extends Application {
        @Override
        public void start(final Stage primaryStage) {
            final Account model = Loader.loadData();
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
            new AccountControllerImpl(model, view, saver);
            view.start(primaryStage);
        }
    }
}
