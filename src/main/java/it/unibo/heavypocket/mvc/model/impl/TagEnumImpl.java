package it.unibo.heavypocket.mvc.model.impl;

import it.unibo.heavypocket.mvc.model.Tag;

/**
 * Implementation of the Tag interface using an enum.
 */
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

    /**
     * Constructor for TagEnumImpl.
     */
    TagEnumImpl() {
    }

    @Override
    public String getName() {
        return this.name();
    }
}
