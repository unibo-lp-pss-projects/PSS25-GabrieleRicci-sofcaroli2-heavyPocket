package it.unibo.heavypocket.mvc.view.panels;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.model.FiltersData;

public interface TransactionListPanel extends Panel {

    void setTransactions(List<Transaction> transactions);

    void setTagList(List<Tag> tags);

    void setOnSearch(Consumer<FiltersData> searchListener);

    void setOnDelete(Consumer<UUID> deleteListener);

    void clearFilters();
}
