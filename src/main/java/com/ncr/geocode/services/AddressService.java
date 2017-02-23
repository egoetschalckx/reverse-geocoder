package com.ncr.geocode.services;

import com.ncr.geocode.models.Address;

import java.util.List;

public interface AddressService {

    List<Address> getCachedAddresses();
    Address get(double lat, double lon);
}
