package com.n11.RestaurantsApp.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointRequest {
    double latitude;
    double longitude;
}
