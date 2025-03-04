package org.example.model;

public enum Cuisine {

    POLISH("Polish"),
    MEXICAN("Mexican"),
    ITALIAN("Italian");

    private final String displayName;

    Cuisine(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
