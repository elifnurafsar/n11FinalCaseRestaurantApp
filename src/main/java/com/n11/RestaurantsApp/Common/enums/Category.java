package com.n11.RestaurantsApp.Common.enums;

public enum Category {
    ASIAN("Asian"),
    ITALIAN("Italian"),
    TURKISH("Turkish"),
    RUSSIAN("Russian"),
    FAST_FOOD("Fast food"),
    AMERICAN("American"),
    FINE_DINING("Fine dining"),
    GENERAL("General");

    private final String category;

    Category(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category;
    }
}