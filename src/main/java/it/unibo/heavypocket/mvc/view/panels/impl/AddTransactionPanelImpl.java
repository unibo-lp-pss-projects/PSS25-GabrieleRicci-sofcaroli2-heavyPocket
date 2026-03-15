package it.unibo.heavypocket.mvc.view.panels.impl;

import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.geometry.Pos;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;

import it.unibo.heavypocket.mvc.DTO.TransactionDTO;
import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.TransactionType;
import it.unibo.heavypocket.mvc.view.panels.AddTransactionPanel;

public final class AddTransactionPanelImpl implements AddTransactionPanel {

    private static final String SELECT_TAG = "Select Tag";

    private final VBox rootPanel = new VBox();
    private final Label typeLabel = new Label("Type:");
    private final ChoiceBox<TransactionType> typeField = new ChoiceBox<>();
    private final TextField amountField = new TextField();
    private final DatePicker datePicker = new DatePicker();
    private final TextField descriptionField = new TextField();
    private final Label tagLabel = new Label("Tag:");
    private final ComboBox<Tag> filterTag = new ComboBox<>();
    private final Button addButton = new Button();
    private final Button resetButton = new Button();
    private Consumer<TransactionDTO> addListener;

    public AddTransactionPanelImpl() {
        initializeFields();
        createLayout();
    }

    @Override
    public Region getRoot() {
        return this.rootPanel;
    }

    @Override
    public void setTagList(final List<Tag> tags) {
        filterTag.getItems().addAll(tags);
    }

    @Override
    public void setOnAdd(final Consumer<TransactionDTO> addListener) {
        this.addListener = addListener;
    }

    @Override
    public void resetFields() {
        amountField.clear();
        datePicker.setValue(LocalDate.now());
        descriptionField.clear();
        typeField.setValue(null);
        filterTag.setValue(null);
        if (addListener != null) {
            addListener.accept(null);
        }
    }

    private void initializeFields() {
        typeField.getItems().addAll(TransactionType.values());
        typeField.setValue(null);
        amountField.setPromptText("0.00");
        datePicker.setValue(LocalDate.now());
        descriptionField.setPromptText("Short description");
        addButton.setText("Add");
        resetButton.setText("Reset");
        addButton.setOnAction(e -> handleAdd());
        resetButton.setOnAction(e -> resetFields());
    }

    private void handleAdd() {
        // if (addListener != null) {
        //     addListener.accept(// @TODO
        // }
        return;
    }

    private void createLayout() {
        rootPanel.setSpacing(5);
        rootPanel.setAlignment(Pos.CENTER);
        final HBox typeAmountRow = new HBox(10, typeLabel, typeField, amountField);
        typeAmountRow.setAlignment(Pos.CENTER);
        final HBox dateTagRow = new HBox(10, datePicker, tagLabel, filterTag);
        dateTagRow.setAlignment(Pos.CENTER);
        final HBox descriptionRow = new HBox(10, descriptionField);
        descriptionRow.setAlignment(Pos.CENTER);
        final HBox buttonRow = new HBox(10, addButton, resetButton);
        buttonRow.setAlignment(Pos.CENTER);
        rootPanel.getChildren().addAll(typeAmountRow, dateTagRow, descriptionRow, buttonRow);
    }
}
