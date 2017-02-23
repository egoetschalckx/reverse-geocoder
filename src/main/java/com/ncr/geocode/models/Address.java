package com.ncr.geocode.models;

import java.time.OffsetDateTime;

public class Address {

    private final String address;
    private final OffsetDateTime ts;
    private final float lat;
    private final float lon;

    public Address(String address, OffsetDateTime ts, float lat, float lon) {
        this.address = address;
        this.ts = ts;
        this.lat = lat;
        this.lon = lon;
    }

    public String getAddress() {
        return address;
    }

    public OffsetDateTime getTs() {
        return ts;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    public String getLatLon() {
        return Float.toString(lat) + "/" + Float.toString(lon);
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                ", ts=" + ts +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
