package com.ncr.geocode.controllers;

import com.ncr.geocode.exceptions.BadRequestException;
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
    private static final int MIN_LAT = -90;
    private static final int MAX_LAT = 90;
    private static final int MIN_LON = -180;
    private static final int MAX_LON = 180;

    private final AddressService addressService;

    @Autowired
    public GeocodeController(
            AddressService addressService
    ) {
        this.addressService = addressService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Address getAddress(
            @RequestParam("lat") double lat,
            @RequestParam("lon") double lon
    ) {

        if (lat < MIN_LAT || lat > MAX_LAT) {
            throw new BadRequestException("lat must be between " + MIN_LAT + " and " + MAX_LAT);
        }
        if (lon < MIN_LON || lon > MAX_LON) {
            throw new BadRequestException("lon must be between " + MIN_LON + " and " + MAX_LON);
        }

        LOGGER.debug("Getting {}/{}", Double.toString(lat), Double.toString(lon));
        return addressService.get(lat, lon);
    }

    @RequestMapping(value = "/cache", method = RequestMethod.GET)
    public List<Address> getCachedAddresses() {
        return addressService.getCachedAddresses();
    }
}
