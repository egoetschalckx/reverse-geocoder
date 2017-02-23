package com.ncr.geocode.services;

import com.ncr.geocode.models.Address;

@FunctionalInterface
public interface WebAddressService {

    Address getAddress(double lat, double lon);
}
