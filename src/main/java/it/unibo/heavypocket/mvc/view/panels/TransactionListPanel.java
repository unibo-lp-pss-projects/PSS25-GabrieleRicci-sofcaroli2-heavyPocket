package it.unibo.heavypocket.mvc.view.panels;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.DTO.FiltersDTO;

public interface TransactionListPanel extends Panel {

    void setTransactions(List<Transaction> transactions);

    void setTagList(List<Tag> tags);

    void setOnSearch(Consumer<FiltersDTO> searchListener);

    void setOnEdit(Consumer<UUID> editListener);

    void setOnDelete(Consumer<UUID> deleteListener);

    void clearFilters();
}
