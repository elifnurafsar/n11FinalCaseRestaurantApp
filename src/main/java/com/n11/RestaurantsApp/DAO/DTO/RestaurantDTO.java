package com.n11.RestaurantsApp.DAO.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.n11.RestaurantsApp.Common.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO extends BaseResponse {
    private String id;
    private String name;
    private String email;
    private String createdAt;
    private Double latitude;
    private Double longitude;
    private Double score;
    private String category;

    @JsonIgnore
    private int errCode = 0;
    @JsonIgnore
    private String errMessage = "";
}

