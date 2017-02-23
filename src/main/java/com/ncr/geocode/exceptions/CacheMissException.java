package com.ncr.geocode.exceptions;

public class CacheMissException extends RuntimeException {

    public CacheMissException(String key) {
        super("Cache miss on " + key);
    }
}
