package com.n11.RestaurantsApp.Common;

import java.io.Serializable;

public interface BaseErrorMessage extends Serializable {
    String getMessage();
    int getCode();
}