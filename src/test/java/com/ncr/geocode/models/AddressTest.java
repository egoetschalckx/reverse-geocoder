package com.ncr.geocode.models;

import org.junit.Test;

import java.time.OffsetDateTime;

import static org.junit.Assert.assertEquals;

public class AddressTest {

    @Test
    public void testAllFields() {
        String addr = "foo";
        OffsetDateTime offsetDateTime = OffsetDateTime.now();
        double lat = 42;
        double lon = 9001;
        String expectedToString = "Address{" + "formattedAddress='" + addr + '\'' + ", ts=" + offsetDateTime + ", lat=" + lat + ", lon=" + lon + '}';

        Address address = new Address(addr, offsetDateTime, lat, lon);

        assertEquals(addr, address.getFormattedAddress());
        assertEquals(offsetDateTime, address.getTs());
        assertEquals(lat, address.getLat(), .000001);
        assertEquals(lon, address.getLon(), .000001);
        assertEquals(expectedToString, address.toString());
    }
}
