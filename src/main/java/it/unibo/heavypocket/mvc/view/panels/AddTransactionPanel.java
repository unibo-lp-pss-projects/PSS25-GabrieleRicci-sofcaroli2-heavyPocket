package it.unibo.heavypocket.mvc.view.panels;

import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import it.unibo.heavypocket.mvc.dto.TransactionDTO;
import it.unibo.heavypocket.mvc.model.Tag;

/**
 * Interface representing the panel for adding transactions. It's also used for
 * editing transactions by filling the fields with the data of * the transaction
 * to edit.
 */
public interface AddTransactionPanel extends Panel {

    /**
     * Sets the list of tags in the panel.
     * 
     * @param tags the list of tags to set in the panel.
     */
    void setTagList(List<Tag> tags);

    /**
     * Sets the listener for the add operation. It takes a transaction dto as input.
     * 
     * @param addListener the listener for the add operation.
     */
    void setOnAdd(Consumer<TransactionDTO> addListener);

    /**
     * Sets the listener for the edit operation. It takes the ID of the transaction
     * to edit and a transaction dto as input.
     * 
     * @param id             the ID of the transaction to edit.
     * @param transactionDTO the transaction dto containing the updated information.
     */
    void editTransaction(UUID id, TransactionDTO transactionDTO);

    /**
     * Sets the listener for the edit operation. It takes the ID of the transaction
     * to edit and a transaction dto as input.
     * 
     * @param editListener the listener for the edit operation.
     */
    void setOnEdit(BiConsumer<UUID, TransactionDTO> editListener);

    /**
     * Resets the fields in the panel.
     */
    void resetFields();
}
