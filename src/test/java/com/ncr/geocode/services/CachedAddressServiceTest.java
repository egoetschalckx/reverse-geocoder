package com.ncr.geocode.services;

import com.ncr.geocode.cache.Cache;
import com.ncr.geocode.models.Address;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CachedAddressServiceTest {

    @InjectMocks
    CachedAddressService cachedAddressService;

    @Mock
    Cache<String, Address> addressCache;

    @Mock
    WebAddressService webAddressService;

    float lat = 42;
    float lon = 9001;

    @Before
    public void before() {
        initMocks(this);
    }

    @Test
    public void testGetCachedAddresses() {
        Address expectedAddress = new Address("foobar", OffsetDateTime.now(), 42f, 9001f);
        List<Address> expectedAddresses = singletonList(expectedAddress);
        when(addressCache.list()).thenReturn(expectedAddresses);

        List<Address> actualAddresses = cachedAddressService.getCachedAddresses();

        assertEquals(1, actualAddresses.size());
        assertEquals(expectedAddresses.size(), actualAddresses.size());
        assertEquals(expectedAddress, actualAddresses.get(0));
    }

    @Test
    public void testGet() {
        when(addressCache.get(any())).thenThrow(NoSuchElementException.class);

        cachedAddressService.get(lat, lon);

        verify(addressCache, times(1)).get(lat + "/" + lon);
        verify(webAddressService, times(1)).getAddress(lat, lon);
        verify(addressCache, times(1)).put(eq(lat + "/" + lon), any());
    }

    @Test
    public void testGetWhenCached() {
        cachedAddressService.get(42f, 9001f);
    }
}
