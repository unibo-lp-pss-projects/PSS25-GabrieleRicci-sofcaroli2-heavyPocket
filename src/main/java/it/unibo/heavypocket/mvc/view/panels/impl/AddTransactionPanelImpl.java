package it.unibo.heavypocket.mvc.view.panels.impl;

import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;
import java.util.UUID;
import java.util.function.BiConsumer;

import it.unibo.heavypocket.mvc.view.panels.impl.TransactionData;
import it.unibo.heavypocket.mvc.view.panels.AddTransactionPanel;
import it.unibo.heavypocket.mvc.model.Transaction;

// @TODO estendere panel
public final class AddTransactionPanelImpl implements AddTransactionPanel {

    private final HBox rootPanel = new HBox();
    private final CheckBox expenseCheckBox = new CheckBox();
    private final TextField amountField = new TextField();
    private final DatePicker datePicker = new DatePicker();
    private final TextField descriptionField = new TextField();
    private final ComboBox<String> tagComboBox = new ComboBox<>();
    private final Button addButton = new Button();
    private final Button resetButton = new Button();
    // private Consumer<TransactionData> addListener;
    // private BiConsumer<UUID, TransactionData> editListenrer;

    public AddTransactionPanelImpl() {
        initializeFields();
        rootPanel.setSpacing(10);
        rootPanel.getChildren().addAll(
                expenseCheckBox,
                amountField,
                datePicker,
                descriptionField,
                tagComboBox,
                addButton,
                resetButton);
    }

    @Override
    public Region getRoot() {
        return this.rootPanel;
    }

    // @Override
    // public void setTagList(final List<String> tags) {
    // this.tagComboBox.getItems().setAll(tags);
    // }

    // @Override
    // public void showTransaction(final Transaction transaction) {
    // amountField.setText(String.valueOf(transaction.getAmount()));
    // datePicker.setValue(transaction.getDate());
    // descriptionField.setText(transaction.getDescription());
    // expenseCheckBox.setSelected(transaction.isExpense());
    // // tagComboBox.setValue(transaction.getTag());
    // }

    // @Override
    // public void setOnAdd(final Consumer<TransactionData> listener) {
    // this.addListener = listener;
    // }

    // @Override
    // public void setOnEdit(final BiConsumer<UUID, TransactionData> listener) {
    // this.editListenrer = listener;
    // }

    @Override
    public void resetFields() {
        amountField.clear();
        datePicker.setValue(LocalDate.now());
        descriptionField.clear();
        expenseCheckBox.setSelected(false);
        tagComboBox.getSelectionModel().clearSelection();
    }

    private void initializeFields() {
        expenseCheckBox.setText("Is Expense");
        amountField.setPromptText("0.00 €");
        datePicker.setValue(LocalDate.now());
        descriptionField.setPromptText("Short description...");
        tagComboBox.setPromptText("Select Tag");
        addButton.setText("Add");
        resetButton.setText("Reset");
        addButton.setOnAction(e -> {
            // @TODO implement add transaction logic
        });
        resetButton.setOnAction(e -> {
            resetFields();
        });
    }
}
