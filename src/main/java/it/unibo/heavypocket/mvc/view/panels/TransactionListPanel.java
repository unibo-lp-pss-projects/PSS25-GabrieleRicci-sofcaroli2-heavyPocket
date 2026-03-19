package it.unibo.heavypocket.mvc.view.panels;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.dto.FiltersDTO;

/**
 * Interface representing the panel that shows the list of transactions, delete
 * operation, a call to controller to edit transactions and and filters.
 */
public interface TransactionListPanel extends Panel {

    /**
     * Sets the list of transactions in the panel.
     *
     * @param transactions the list of transactions to set in the panel.
     */
    void setTransactions(List<Transaction> transactions);

    /**
     * Sets the list of tags in the panel.
     * 
     * @param tags the list of tags to set in the panel.
     */
    void setTagList(List<Tag> tags);

    /**
     * Sets the listener for the search operation. It takes filters dto as input.
     * 
     * @param searchListener the listener for the search operation.
     */
    void setOnSearch(Consumer<FiltersDTO> searchListener);

    /**
     * Sets the listener for the edit operation. It takes the ID of the transaction
     * to edit as input.
     * 
     * @param editListener the listener for the edit operation.
     */
    void setOnEdit(Consumer<UUID> editListener);

    /**
     * Sets the listener for the delete operation.
     * 
     * @param deleteListener id of the transaction to delete.
     */
    void setOnDelete(Consumer<UUID> deleteListener);

    /**
     * Clears the filters in the panel.
     */
    void clearFilters();
}
