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

import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.view.panels.AddTransactionPanel;

public final class AddTransactionPanelImpl implements AddTransactionPanel {

    private final static String SELECT_TAG = "Select Tag";

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

    @Override
    public void setTagList(final List<Tag> tags) {
        final List<String> tagsName = tags.stream().map(Tag::getName).toList();
        tagComboBox.getItems().add(SELECT_TAG);
        tagComboBox.getItems().addAll(tagsName);
        tagComboBox.setPromptText(SELECT_TAG);
    }

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
        tagComboBox.setValue(SELECT_TAG);
    }

    private void initializeFields() {
        expenseCheckBox.setText("Is Expense");
        amountField.setPromptText("0.00 €");
        datePicker.setValue(LocalDate.now());
        descriptionField.setPromptText("Short description...");
        addButton.setText("Add");
        resetButton.setText("Reset");
        addButton.setOnAction(e -> {
            // @TODO
        });
        resetButton.setOnAction(e -> {
            resetFields();
        });
    }
}
