package it.unibo.heavypocket.mvc.view.panels;

import java.util.List;

import it.unibo.heavypocket.mvc.model.Transaction;

//@TODO estendere panel
public interface TransactionListPanel {
    
    void setTransactions(List<Transaction> transactions);

    void updateTransactions(List<Transaction> transactions);

    // @TODO
    void setOnSearch();

    void clearFilters();
}
