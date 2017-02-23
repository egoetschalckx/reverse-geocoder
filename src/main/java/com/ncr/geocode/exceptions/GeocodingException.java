package com.ncr.geocode.exceptions;

public class GeocodingException extends RuntimeException {

    public GeocodingException(String geocodeProvider, String message, Throwable throwable) {
        super(geocodeProvider + " geocoding error - " + message, throwable);
    }
}
