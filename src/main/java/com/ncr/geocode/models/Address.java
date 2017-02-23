package com.ncr.geocode.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.OffsetDateTime;

public class Address {

    private final String formattedAddress;
    private final OffsetDateTime ts;
    private final double lat;
    private final double lon;

    public Address(String formattedAddress, OffsetDateTime ts, double lat, double lon) {
        this.formattedAddress = formattedAddress;
        this.ts = ts;
        this.lat = lat;
        this.lon = lon;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public OffsetDateTime getTs() {
        return ts;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    @JsonIgnore
    public String getLatLon() {
        return Double.toString(lat) + "/" + Double.toString(lon);
    }

    @Override
    public String toString() {
        return "Address{" + "formattedAddress='" + formattedAddress + '\'' + ", ts=" + ts + ", lat=" + lat + ", lon=" + lon + '}';
    }
}
