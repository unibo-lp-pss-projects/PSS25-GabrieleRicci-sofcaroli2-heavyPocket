package it.unibo.heavypocket.mvc.model.impl;

import it.unibo.heavypocket.mvc.model.Tag;

public enum TagEnumImpl implements Tag {
    FOOD("#FF5733"),
    TRANSPORT("#3357FF"),
    ENTERTAINMENT("#F033FF"),
    UTILITIES("#33FFF5"),
    OTHER("#808080"),
    SALARY("#33FF57"),
    INVESTMENT("#FFD700"),
    GIFT("#FF69B4"),
    HEALTHCARE("#FF0000"),
    EDUCATION("#4B0082");

    private final String color;

    TagEnumImpl(final String color) {
        this.color = color;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String getColor() {
        return this.color;
    }
}
