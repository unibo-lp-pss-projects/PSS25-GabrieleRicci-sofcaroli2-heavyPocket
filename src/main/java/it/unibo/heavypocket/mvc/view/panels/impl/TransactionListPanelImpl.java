package it.unibo.heavypocket.mvc.view.panels.impl;

import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.util.function.Consumer;
import java.util.List;

import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.view.panels.TransactionListPanel;

// @TODO estendere panel
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
        initializeSearchBar();
        initializeListView();
        panelRoot.getChildren().addAll(populateSearchBar(), transactionListView);
    }

    public Region getPanelRoot(){
        return this.panelRoot;
    }

    @Override
    public void setTransactions(List<Transaction> transactions) {
        transactionListView.getItems().setAll(transactions);
    }

    @Override
    public void setOnSearch(Consumer<String> listener) {
        this.searchListener = listener;
    }

    @Override
    public void clearFilters() {
        filterType.setValue(null);
        filterDate.setValue(null);
        filterTag.setValue(null);
        if (searchListener != null) {
            searchListener.accept("");
        }
    }

    //@TODO controllare il tipo %s
    private void initializeListView() {
        transactionListView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Transaction transaction, boolean empty) {
                super.updateItem(transaction, empty);
                if (empty || transaction == null) {
                    setText(null);
                } else {
                    setText(String.format("%s - %s - %s - %s - %s",
                            transaction.isExpense() ? "-" : "+",
                            transaction.getAmount(),
                            transaction.getDate(),
                            transaction.getDescription(),
                            transaction.getTag()));
                }
            }
        });
    }

    private HBox populateSearchBar() {
        final HBox searchBar = new HBox();
        searchBar.getChildren().addAll(filterType, filterDate, filterTag, searchButton, clearFiltersButton);
        return searchBar;
    }

    //@TODO aggiungere valori alle choice box
    private void initializeSearchBar() {
        filterDate.setPromptText("Choose a date");
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

}
