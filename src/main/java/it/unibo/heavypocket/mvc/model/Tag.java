package it.unibo.heavypocket.mvc.model;

public interface Tag {

    String getName();

    String getColor();

    TagType getType();

    enum TagType {
        EXPENSE,
        INCOME
    }
}
