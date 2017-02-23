package com.ncr.geocode.exceptions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GeocodingExceptionTest {

    @Test
    public void testConstructor() {
        String provider = "foo";
        String msg = "bar";

        GeocodingException exception = new GeocodingException(provider, msg, new Exception());

        assertEquals(provider + " geocoding error - " + msg, exception.getMessage());
    }
}
