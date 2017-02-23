package com.ncr.geocode.configs;

import com.google.maps.GeoApiContext;
import com.ncr.geocode.configs.GoogleMapsConfig;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GoogleMapsConfigTest {

    @Test
    public void test() {
        GoogleMapsConfig googleMapsConfig = new GoogleMapsConfig();

        GeoApiContext geoApiContext = googleMapsConfig.geoApiContext("foo");

        assertNotNull(geoApiContext);
    }
}
