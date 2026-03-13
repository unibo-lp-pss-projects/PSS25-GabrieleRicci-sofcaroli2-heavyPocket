package it.unibo.heavypocket.mvc.view.panels;

import java.util.List;

import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Transaction;

public interface TransactionListPanel extends Panel {

    void setTransactions(List<Transaction> transactions);

    void setTagList(List<Tag> tags);

    // void setOnDelete(Consumer<UUID> listener);

    // void setOnSearch(Consumer<String> listener);

    void clearFilters();
}
