package it.unibo.heavypocket.mvc.view.panels;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import it.unibo.heavypocket.mvc.model.Tag;
import it.unibo.heavypocket.mvc.model.Transaction;

//@TODO estendere panel
public interface TransactionListPanel {
    
    void setTransactions(List<Transaction> transactions);

    void setTagList(List<Tag> tags);

    void setOnSearch(Consumer<String> listener);

    void clearFilters();
}
