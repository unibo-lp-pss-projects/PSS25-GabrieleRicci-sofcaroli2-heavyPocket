package it.unibo.heavypocket;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import it.unibo.heavypocket.mvc.view.panels.TransactionListPanel;
import it.unibo.heavypocket.mvc.view.panels.impl.TransactionListPanelImpl;

public final class JavaFXApp extends Application {

    @Override
    public void start(final Stage primaryStage) {
        
        //@TEST
        final TransactionListPanelImpl view = new TransactionListPanelImpl();
        final Scene scene = new Scene(view.getPanelRoot(), 800, 600);
        
        primaryStage.setTitle("Hello");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static final class Main {
        private Main() {
            // the constructor will never be called directly.
        }

        public static void main(final String... args) {
            launch(JavaFXApp.class, args);
        }
    }
}
