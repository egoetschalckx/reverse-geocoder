package com.ncr.geocode.services;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.ncr.geocode.exceptions.GeocodingException;
import com.ncr.geocode.models.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
public class GoogleAddressService implements WebAddressService {

    private final GeoApiContext geoApiContext;

    @Autowired
    public GoogleAddressService(GeoApiContext geoApiContext) {
        this.geoApiContext = geoApiContext;
    }

    @Override
    public Address getAddress(double lat, double lon) {
        try {
            LatLng latLng = new LatLng(lat, lon);
            GeocodingApiRequest geocodingApiRequest = GeocodingApi.reverseGeocode(geoApiContext, latLng);
            GeocodingResult[] results = geocodingApiRequest.await();
            return new Address(
                    results[0].formattedAddress,
                    OffsetDateTime.now(),
                    lat,
                    lon);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new GeocodingException("Google Maps", "no addresses found", e);
        } catch (Exception e) {
            throw new GeocodingException("Google Maps", e.getMessage(), e);
        }
    }
}
