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

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.List;
import java.util.UUID;

import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.view.panels.TransactionListPanel;
import it.unibo.heavypocket.mvc.model.Tag;

public final class TransactionListPanelImpl implements TransactionListPanel {

    private static final String ALL_TAGS = "All tags";
    private static final String ALL = "All";
    private static final String EXPENSE = "Expense";
    private static final String INCOME = "Income";
    private static final String DATE_FORMAT = "YYYY-MM-DD";

    private final VBox rootPanel = new VBox();
    private final ListView<Transaction> transactionList = new ListView<>();
    private final ListView<Tag> tagList = new ListView<>();
    // private final List<String> tagsName = new ArrayList<>();
    private final ChoiceBox<String> filterType = new ChoiceBox<>();
    private final DatePicker filterDate = new DatePicker();
    private final ComboBox<String> filterTag = new ComboBox<>();
    private final Button searchButton = new Button("Search");
    private final Button clearFiltersButton = new Button("Clear Filters");
    // private Consumer<UUID> deleteListener;
    // private Consumer<String> searchListener;

    public TransactionListPanelImpl() {
        initializeSearchBar();
        initializeTransactionList();
        rootPanel.getChildren().addAll(populateSearchBar(), transactionList, tagList);
    }

    @Override
    public Region getRoot() {
        return this.rootPanel;
    }

    @Override
    public void setTransactions(final List<Transaction> transactions) {
        this.transactionList.getItems().setAll(transactions);
    }

    @Override
    public void setTagList(final List<Tag> tags) {
        this.tagList.getItems().setAll(tags);
        // this.tagsName = tags.stream().map(Tag::getName).toList();
    }

    // @Override
    // public void setOnDelete(final Consumer<UUID> listener) {
    // this.deleteListener = listener;
    // }

    // @Override
    // public void setOnSearch(final Consumer<String> listener) {
    // this.searchListener = listener;
    // }

    @Override
    public void clearFilters() {
        filterType.setValue(ALL);
        filterDate.setValue(null);
        filterTag.setValue(ALL_TAGS);
        // if (searchListener != null) {
        //     searchListener.accept("");
        // }
    }

    // @TODO controllare il tipo %s
    private void initializeTransactionList() {
        transactionList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(final Transaction transaction, final boolean empty) {
                super.updateItem(transaction, empty);
                if (empty || transaction == null) {
                    setText(null);
                } else {
                    setText(String.format("%s %s | %s | %s | %s",
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
        searchBar.getChildren().addAll(filterType, filterDate, filterTag,
                searchButton, clearFiltersButton);
        return searchBar;
    }

    private void initializeSearchBar() {
        filterType.getItems().addAll(ALL, EXPENSE, INCOME);
        filterType.setValue(ALL);
        filterDate.setPromptText(DATE_FORMAT);
        filterTag.getItems().add(ALL_TAGS);
        // filterTag.getItems().addAll(tagsName);
        filterTag.setValue(ALL_TAGS);
        searchButton.setOnAction(e -> handleSearch());
        clearFiltersButton.setOnAction(e -> clearFilters());
    }

    private void handleSearch() {
        final String type = filterType.getValue();
        final String date = filterDate.getValue() != null ? filterDate.getValue().toString() : "";
        final String tag = filterTag.getValue();
        final String searchQuery = String.format("type:%s date:%s tag:%s", type, date, tag);
        // if (searchListener != null) {
        //     searchListener.accept(searchQuery);
        // }
    }
}
