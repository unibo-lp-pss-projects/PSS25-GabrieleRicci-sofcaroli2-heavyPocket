package it.unibo.heavypocket.mvc.view.panels;

import java.util.List;

import it.unibo.heavypocket.mvc.model.Tag;

public interface AddTransactionPanel extends Panel {

    void setTagList(List<Tag> tags);

    // void showTransaction(Transaction transaction);

    // void setOnAdd(Consumer<TransactionData> listener);

    // void setOnEdit(BiConsumer<UUID, TransactionData> listener);

    void resetFields();
}
