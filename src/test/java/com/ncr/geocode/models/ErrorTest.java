package com.ncr.geocode.models;

import org.junit.Test;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class ErrorTest {

    @Test
    public void testGetMsg() {
        List<String> errors = singletonList("foo");

        Error error = new Error(errors);

        assertEquals(errors, error.getErrors());
    }
}
