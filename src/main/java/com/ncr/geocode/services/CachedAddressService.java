package com.ncr.geocode.services;

import com.ncr.geocode.cache.Cache;
import com.ncr.geocode.controllers.GeocodeController;
import com.ncr.geocode.models.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class CachedAddressService implements AddressService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeocodeController.class);

    final Cache<String, Address> addressCache;
    final WebAddressService webAddressService;

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
    public Address get(float lat, float lon) {
        String latLon = lat + "/" + lon;

        try {
            Address address = addressCache.get(latLon);
            LOGGER.debug("Cache hit on {}", latLon);
            return address;
        } catch (NoSuchElementException e) {
            LOGGER.debug("Cache miss on {}", latLon, e);
            Address addressFromWeb = webAddressService.getAddress(lat, lon);
            addressCache.put(latLon, addressFromWeb);
            return addressFromWeb;
        }
    }
}
