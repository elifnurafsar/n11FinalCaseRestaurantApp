package com.n11.RestaurantsApp.Common;

import lombok.Data;

@Data
public class BaseResponse {
    private int errCode;
    private String errMessage;
}