package com.ncr.geocode.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ErrorTest {

    @Test
    public void testGetMsg() {
        String msg = "foo";

        Error error = new Error(msg);

        assertEquals(msg, error.getMsg());
    }
}
