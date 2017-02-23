package com.ncr.geocode.controllers;

import com.ncr.geocode.exceptions.BadRequestException;
import com.ncr.geocode.models.Address;
import com.ncr.geocode.services.AddressService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.OffsetDateTime;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GeocoderControllerTest {

    @InjectMocks
    GeocodeController geocodeController;

    @Mock
    AddressService addressService;

    @Before
    public void before() {
        initMocks(this);
    }

    @Test
    public void testGetAddress() {
        Address expectedAddress = new Address("foobar", OffsetDateTime.now(), 42f, 9001f);
        when(addressService.get(anyDouble(), anyDouble())).thenReturn(expectedAddress);

        Address actualAddress = geocodeController.getAddress(1, 2);

        assertEquals(expectedAddress, actualAddress);
    }

    @Test(expected = BadRequestException.class)
    public void testGetAddressLatTooSmall() {
        geocodeController.getAddress(-91, 2);
    }

    @Test(expected = BadRequestException.class)
    public void testGetAddressLatTooLarge() {
        geocodeController.getAddress(91, 2);
    }

    @Test(expected = BadRequestException.class)
    public void testGetAddressLonTooSmall() {
        geocodeController.getAddress(2, -181);
    }

    @Test(expected = BadRequestException.class)
    public void testGetAddressLonTooLarge() {
        geocodeController.getAddress(2, 181);
    }

    @Test
    public void testGetCachedAddresses() {
        Address expectedAddress = new Address("foobar", OffsetDateTime.now(), 42f, 9001f);
        List<Address> expectedAddresses = singletonList(expectedAddress);
        when(addressService.getCachedAddresses()).thenReturn(expectedAddresses);

        List<Address> actualAddresses = geocodeController.getCachedAddresses();

        assertEquals(1, actualAddresses.size());
        assertEquals(expectedAddresses.size(), actualAddresses.size());
        assertEquals(expectedAddress, actualAddresses.get(0));
    }
}
