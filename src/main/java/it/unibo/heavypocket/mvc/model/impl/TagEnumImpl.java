package it.unibo.heavypocket.mvc.model.impl;

import it.unibo.heavypocket.mvc.model.Tag;

public enum TagEnumImpl implements Tag {
    FOOD("#FF5733", TagType.EXPENSE),
    TRANSPORT("#3357FF", TagType.EXPENSE),
    ENTERTAINMENT("#F033FF", TagType.EXPENSE),
    EDUCATION("#4B0082", TagType.EXPENSE),
    HOME("#33FFF5", TagType.EXPENSE),
    INVESTMENT("#FFD700", TagType.EXPENSE),
    HEALTHCARE("#FF0000", TagType.EXPENSE),
    GIFT("#FF69B4", TagType.INCOME),
    SALARY("#33FF57", TagType.INCOME);

    private final String color;
    private final TagType type;

    TagEnumImpl(final String color, final TagType type) {
        this.color = color;
        this.type = type;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public TagType getType() {
        return this.type;
    }
}
