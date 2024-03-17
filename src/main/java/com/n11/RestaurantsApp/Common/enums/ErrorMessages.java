package com.n11.RestaurantsApp.Common.enums;


import com.n11.RestaurantsApp.Common.BaseErrorMessage;

public enum ErrorMessages implements BaseErrorMessage {

    DEFAULT("An unexpected error occurred!", 4000),
    RESTAURANT_NOT_FOUND("Restaurant not found!", 4001),
    SOLR_ERROR("An error occurred while connecting to Solr.", 4005);

    private final String message;
    private final int code;

    ErrorMessages(String message, int code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return message;
    }
}

