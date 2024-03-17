package com.n11.RestaurantsApp.Request;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointRequestTest {

    @Test
    public void testConstructorAndGetters() {
        double latitude = 3.46675;
        double longitude = 56.4568;

        PointRequest pointRequest = new PointRequest(latitude, longitude);

        assertEquals(latitude, pointRequest.getLatitude());
        assertEquals(longitude, pointRequest.getLongitude());
    }

    @Test
    public void testSetter() {
        double latitude = 3.46675;
        double longitude = 56.4568;

        PointRequest pointRequest = new PointRequest();
        pointRequest.setLatitude(latitude);
        pointRequest.setLongitude(longitude);

        assertEquals(latitude, pointRequest.getLatitude());
        assertEquals(longitude, pointRequest.getLongitude());
    }
}