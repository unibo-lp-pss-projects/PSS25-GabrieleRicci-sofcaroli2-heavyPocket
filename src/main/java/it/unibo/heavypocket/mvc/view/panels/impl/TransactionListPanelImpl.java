package it.unibo.heavypocket.mvc.view.panels.impl;

import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;

import java.util.function.Consumer;
import java.util.List;

import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.view.panels.TransactionListPanel;

public class TransactionListPanelImpl implements TransactionListPanel {

    private final VBox panelRoot = new VBox();
    private final ListView<Transaction> transactionListView = new ListView<>();
    private final ChoiceBox<String> filterType = new ChoiceBox<>();
    private final DatePicker filterDate = new DatePicker();
    private final ComboBox<String> filterTag = new ComboBox<>();
    private final Button searchButton = new Button("Search");
    private final Button clearFiltersButton = new Button("Clear Filters");
    private Consumer<String> searchListener;

    public TransactionListPanelImpl() {
        initilizaSearchBarFields();
    }

    // TODO
    @Override
    public void setTransactions(List<Transaction> transactions) {
    }

    // TODO
    @Override
    public void updateTransactions(List<Transaction> transactions) {
    }

    // TODO
    @Override
    public void setOnSearch(Consumer<String> listener) {
    }

    private void initilizaSearchBarFields() {
        filterType.setPlaceholder("Select a type");
        filterDate.setPromptText("Choose a date");
        filterTag.setPlaceholder("Select a Tag");
        searchButton.setOnAction(e -> handleSearch());
        clearFiltersButton.setOnAction(e -> clearFilters());
    }

    private void handleSearch() {
        final String type = filterType.getValue();
        final String date = filterDate.getValue() != null ? filterDate.getValue().toString() : "";
        final String tag = filterTag.getValue();
        final String searchQuery = String.format("type:%s date:%s tag:%s", type, date, tag);
        if (searchListener != null) {
            searchListener.accept(searchQuery);
        }
    }

    @Override
    private void clearFilters() {
        filterType.setValue(null);
        filterDate.setValue(null);
        filterTag.setValue(null);
        if (searchListener != null) {
            searchListener.accept("");
        }
    }
}
