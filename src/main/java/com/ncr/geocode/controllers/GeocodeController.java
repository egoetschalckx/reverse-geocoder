package com.ncr.geocode.controllers;

import com.ncr.geocode.models.Address;
import com.ncr.geocode.services.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("address")
public class GeocodeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeocodeController.class);

    private final AddressService addressService;

    @Autowired
    public GeocodeController(
            AddressService addressService
    ) {
        this.addressService = addressService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Address getAddress(
            @RequestParam("lat") float lat,
            @RequestParam("lon") float lon
    ) {
        LOGGER.debug("Getting {}/{}", lat, lon);
        return addressService.get(lat, lon);
    }

    @RequestMapping(value = "/cache", method = RequestMethod.GET)
    public List<Address> getCachedAddresses() {
        return addressService.getCachedAddresses();
    }
}
