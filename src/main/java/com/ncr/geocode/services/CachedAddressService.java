package com.ncr.geocode.services;

import com.ncr.geocode.cache.Cache;
import com.ncr.geocode.controllers.GeocodeController;
import com.ncr.geocode.exceptions.CacheMissException;
import com.ncr.geocode.models.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CachedAddressService implements AddressService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeocodeController.class);

    private final Cache<String, Address> addressCache;
    private final WebAddressService webAddressService;

    @Autowired
    public CachedAddressService(
            Cache<String, Address> addressCache,
            WebAddressService webAddressService
    ) {
        this.addressCache = addressCache;
        this.webAddressService = webAddressService;
    }

    @Override
    public List<Address> getCachedAddresses() {
        return addressCache.list();
    }

    @Override
    public Address get(double lat, double lon) {
        String latLon = lat + "/" + lon;

        try {
            Address address = addressCache.get(latLon);
            LOGGER.debug("Cache hit on {}", latLon);
            return address;
        } catch (CacheMissException e) {
            LOGGER.trace("Cache miss on " + latLon, e);
            Address addressFromWeb = webAddressService.getAddress(lat, lon);
            addressCache.put(latLon, addressFromWeb);
            return addressFromWeb;
        }
    }
}
