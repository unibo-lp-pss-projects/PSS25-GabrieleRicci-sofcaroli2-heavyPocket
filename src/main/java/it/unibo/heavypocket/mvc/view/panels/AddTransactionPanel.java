package it.unibo.heavypocket.mvc.view.panels;

import java.util.List;
import java.util.function.Consumer;

import it.unibo.heavypocket.mvc.DTO.TransactionDTO;
import it.unibo.heavypocket.mvc.model.Tag;

public interface AddTransactionPanel extends Panel {

    void setTagList(List<Tag> tags);

    void setOnAdd(Consumer<TransactionDTO> addListener);

    void resetFields();
}
