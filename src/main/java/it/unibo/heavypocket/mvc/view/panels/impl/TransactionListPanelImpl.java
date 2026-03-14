package it.unibo.heavypocket.mvc.view.panels.impl;

import javafx.scene.layout.VBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.TransactionType;
import it.unibo.heavypocket.mvc.model.FiltersData;
import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.view.panels.TransactionListPanel;

public final class TransactionListPanelImpl implements TransactionListPanel {

    private static final String DATE_FORMAT = "YYYY-MM-DD";

    private final VBox rootPanel = new VBox();
    private final ListView<Transaction> transactionList = new ListView<>();
    private final Label filterTypeLabel = new Label("Type:");
    private final ChoiceBox<TransactionType> filterType = new ChoiceBox<>();
    private final DatePicker filterDate = new DatePicker();
    private final Label filterTagLabel = new Label("Tag:");
    private final ComboBox<Tag> filterTag = new ComboBox<>();
    private final Button searchButton = new Button("Search");
    private final Button clearFiltersButton = new Button("Clear Filters");
    private Consumer<FiltersData> searchListener;

    public TransactionListPanelImpl() {
        initializeSearchBar();
        initializeTransactionList();
        rootPanel.setSpacing(10);
        rootPanel.getChildren().addAll(populateSearchBar(), transactionList);
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
        filterTag.getItems().addAll(tags);
    }

    @Override
    public void setOnSearch(final Consumer<FiltersData> searchListener) {
        this.searchListener = searchListener;
    }

    @Override
    public void clearFilters() {
        filterType.setValue(null);
        filterDate.setValue(null);
        filterTag.setValue(null);
        if (searchListener != null) {
            handleSearch();
        }
    }

    private void initializeTransactionList() {
        transactionList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(final Transaction transaction, final boolean empty) {
                super.updateItem(transaction, empty);
                if (empty || transaction == null) {
                    setText(null);
                } else {
                    setText(String.format("%s %s | %s | %s | %s",
                            transaction.getType() == TransactionType.EXPENSE ? "-" : "+",
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
        searchBar.setSpacing(10);
        searchBar.getChildren().addAll(
                filterTypeLabel,
                filterType,
                filterDate,
                filterTagLabel,
                filterTag,
                searchButton,
                clearFiltersButton);
        return searchBar;
    }

    private void initializeSearchBar() {
        filterType.getItems().addAll(TransactionType.values());
        filterType.setValue(null);
        filterDate.setPromptText(DATE_FORMAT);
        searchButton.setOnAction(e -> handleSearch());
        clearFiltersButton.setOnAction(e -> clearFilters());
    }

    private void handleSearch() {
        if (searchListener != null) {
            searchListener.accept(
                    new FiltersData(
                            filterDate.getValue(),
                            filterType.getValue(),
                            filterTag.getValue()));
        }
    }
}
