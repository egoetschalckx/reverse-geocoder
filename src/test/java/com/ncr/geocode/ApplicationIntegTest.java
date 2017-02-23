package com.ncr.geocode;

import com.ncr.geocode.cache.Cache;
import com.ncr.geocode.controllers.GeocodeController;
import com.ncr.geocode.exceptions.GeocodingException;
import com.ncr.geocode.models.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationIntegTest {

    @Autowired
    GeocodeController geocodeController;

    @Autowired
    Cache<String, Address> cache;

    @Test
    public void testGetAddress() {
        geocodeController.getAddress(33.748995, -84.387982);
        geocodeController.getAddress(33.748995, -84.387982);
        geocodeController.getAddress(33.748995, -84.387982);

        geocodeController.getAddress(33.772496, -84.394717);
        geocodeController.getAddress(33.781109, -84.386373);

        List<Address> list = cache.list();
        assertEquals(2, list.size());
        assertEquals("33.772496/-84.394717", list.get(0).getLatLon());
        assertEquals("33.781109/-84.386373", list.get(1).getLatLon());
    }

    @Test(expected = GeocodingException.class)
    public void testGetAddressWithZeroes() {
        geocodeController.getAddress(0, 0);
    }
}
