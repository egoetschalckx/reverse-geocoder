package com.google.maps;

import com.google.maps.model.GeocodingResult;
import com.ncr.geocode.exceptions.GeocodingException;
import com.ncr.geocode.models.Address;
import com.ncr.geocode.services.GoogleAddressService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.OffsetDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * NOTE: geoApiContext.get() is package-private so I put this test class here as a workaround
 */
public class GoogleAddressServiceTest  {

    private static final Double LATITUDE = -38.010403;
    private static final Double LONGITUDE = -57.558408;

    @Mock
    GeoApiContext geoApiContext;

    @InjectMocks
    GoogleAddressService googleAddressService;

    @Before
    public void before() {
        initMocks(this);
    }

    @Test
    public void testGetAddress() throws Exception {
        Address expectedAddress = new Address("foobar", OffsetDateTime.now(), LATITUDE, LONGITUDE);
        GeocodingResult geocodingResult = new GeocodingResult();
        geocodingResult.formattedAddress = "foobar";
        GeocodingResult[] geocodingResults = new GeocodingResult[] { geocodingResult };
        PendingResult<GeocodingResult[]> pendingResult = mock(PendingResult.class);
        when(pendingResult.await()).thenReturn(geocodingResults);

        when(geoApiContext.get(any(), any(), anyMap())).thenReturn(pendingResult);

        Address actualAddress = googleAddressService.getAddress(LATITUDE, LONGITUDE);

        assertEquals(expectedAddress.getFormattedAddress(), actualAddress.getFormattedAddress());
        assertEquals(expectedAddress.getLatLon(), actualAddress.getLatLon());
    }

    @Test(expected = GeocodingException.class)
    public void testGetAddressWhenException() throws Exception {
        when(geoApiContext.get(any(), any(), anyMap())).thenThrow(new RuntimeException());

        googleAddressService.getAddress(LATITUDE, LONGITUDE);
    }
}
