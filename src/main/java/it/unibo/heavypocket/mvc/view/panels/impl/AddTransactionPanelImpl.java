package it.unibo.heavypocket.mvc.view.panels.impl;

import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;
import java.util.UUID;
import java.util.function.BiConsumer;

import it.unibo.heavypocket.mvc.view.panels.AddTransactionPanel;
import it.unibo.heavypocket.mvc.view.panels.impl.TransactionData;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.Tag;

// @TODO estendere panel
public class AddTransactionPanelImpl implements AddTransactionPanel {

    private final VBox rootPanel = new VBox();
    private final TextField amountField = new TextField();
    private final DatePicker datePicker = new DatePicker();
    private final TextField descriptionField = new TextField();
    private final CheckBox expenseCheckBox = new CheckBox();
    private final ComboBox<String> tagComboBox = new ComboBox<>();
    private final Button addButton = new Button();
    private Consumer<TransactionData> addListener;
    private BiConsumer<UUID, TransactionData> editListenrer;

    public AddTransactionPanelImpl() {
        initializeFields();
        initializeLayout();
    }

    public Region getRootPanel() {
        return this.rootPanel;
    }

    @Override
    public void setTagList(List<String> tags) {
        this.tagComboBox.getItems().setAll(tags);
    }

    @Override
    public void showTransaction(Transaction transaction) {
        amountField.setText(String.valueOf(transaction.getAmount()));
        datePicker.setValue(transaction.getDate());
        descriptionField.setText(transaction.getDescription());
        expenseCheckBox.setSelected(transaction.isExpense());
        // tagComboBox.setValue(transaction.getTag());
    }

    @Override
    public void setOnAdd(Consumer<TransactionData> listener) {
        this.addListener = listener;
    }

    @Override
    public void setOnEdit(BiConsumer<UUID, TransactionData> listener) {
        this.editListenrer = listener;
    }

    private void initializeFields() {
        amountField.setPromptText("0.00 €");
        datePicker.setValue(LocalDate.now());
        descriptionField.setPromptText("Short description...");
        expenseCheckBox.setText("Is Expense");
        tagComboBox.setPromptText("Select Tag");
        addButton.setText("Add Transaction");
        addButton.setOnAction(e -> {
            // @TODO implement add transaction logic
        });
    }

    private void initializeLayout() {
        rootPanel.getChildren().addAll(
                amountField,
                datePicker,
                descriptionField,
                expenseCheckBox,
                tagComboBox,
                addButton);
    }
}
