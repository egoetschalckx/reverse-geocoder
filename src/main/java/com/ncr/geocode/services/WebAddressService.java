package com.ncr.geocode.services;

import com.ncr.geocode.models.Address;

public interface WebAddressService {

    Address getAddress(float lat, float lon);
}
