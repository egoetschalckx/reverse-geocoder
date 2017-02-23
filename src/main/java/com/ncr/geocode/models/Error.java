package com.ncr.geocode.models;

import java.util.List;

import static java.util.Collections.singletonList;

public class Error {

    private final List<String> errors;

    public Error(List<String> errors) {
        this.errors = errors;
    }

    public Error(String errors) {
        this.errors = singletonList(errors);
    }

    public List<String> getErrors() {
        return errors;
    }
}
