package it.unibo.heavypocket.mvc.model.impl;

import it.unibo.heavypocket.mvc.model.Tag;

public enum TagEnumImpl implements Tag {
    FOOD(),
    TRANSPORT(),
    ENTERTAINMENT(),
    EDUCATION(),
    HOME(),
    INVESTMENT(),
    HEALTHCARE(),
    GIFT(),
    SALARY();

    TagEnumImpl() {
    }

    @Override
    public String getName() {
        return this.name();
    }
}
