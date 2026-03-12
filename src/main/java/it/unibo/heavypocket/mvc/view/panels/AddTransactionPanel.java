package it.unibo.heavypocket.mvc.view.panels;

import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.BiConsumer;

import it.unibo.heavypocket.mvc.model.Transaction;
import it.unibo.heavypocket.mvc.view.panels.impl.TransactionData;

//@TODO estendere panel
public interface AddTransactionPanel {

    void setTagList(List<String> tags);

    void showTransaction(Transaction transaction);

    void setOnAdd(Consumer<TransactionData> listener);

    void setOnEdit(BiConsumer<UUID, TransactionData> listener);
}
