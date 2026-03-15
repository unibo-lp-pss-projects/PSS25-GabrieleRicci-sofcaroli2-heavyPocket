package it.unibo.heavypocket.mvc.view.panels;

import java.util.List;

import it.unibo.heavypocket.mvc.model.Tag;

public interface AddTransactionPanel extends Panel {

    void setTagList(List<Tag> tags);

    void resetFields();
}
