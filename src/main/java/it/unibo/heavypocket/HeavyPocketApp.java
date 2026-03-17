package it.unibo.heavypocket;

import javafx.application.Application;

import it.unibo.heavypocket.mvc.view.impl.AccountViewImpl;

// @TOOD: istanziare qua le cose al posto che in view?
public final class HeavyPocketApp {

    public static final class Main {
        private Main() {
            // the constructor will never be called directly.
        }

        public static void main(final String... args) {
            Application.launch(AccountViewImpl.class, args);
        }
    }
}
