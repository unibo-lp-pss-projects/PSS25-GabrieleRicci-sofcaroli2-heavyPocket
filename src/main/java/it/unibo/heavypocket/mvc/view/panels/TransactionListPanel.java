package it.unibo.heavypocket.mvc.view.panels;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Transaction;

//@TODO estendere panel
public interface TransactionListPanel {

    void setTransactions(List<Transaction> transactions);

    void setTagList(List<Tag> tags);

    void setOnDelete(Consumer<UUID> listener);

    void setOnSearch(Consumer<String> listener);

    void clearFilters();
}
