package com.ncr.geocode.models;

import org.junit.Test;

import java.time.OffsetDateTime;

import static org.junit.Assert.assertEquals;

public class AddressTest {

    @Test
    public void testAllFields() {
        String addr = "foo";
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        float lat = 42;
        float lon = 9001;
        String expectedToString = "Address{" + "address='" + addr + '\'' + ", ts=" + offsetDateTime + ", lat=" + lat + ", lon=" + lon + '}';

        Address address = new Address(addr, offsetDateTime, lat, lon);

        assertEquals(addr, address.getAddress());
        assertEquals(offsetDateTime, address.getTs());
        assertEquals(lat, address.getLat(), .1);
        assertEquals(lon, address.getLon(), .1);
        assertEquals(expectedToString, address.toString());
    }
}
