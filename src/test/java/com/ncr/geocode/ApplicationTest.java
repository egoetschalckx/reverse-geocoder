package com.ncr.geocode;

import com.ncr.geocode.cache.Cache;
import com.ncr.geocode.controllers.GeocodeController;
import com.ncr.geocode.exceptions.GeocodingException;
import com.ncr.geocode.models.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

    @Autowired
    GeocodeController geocodeController;

    @Autowired
    Cache<String, Address> cache;

    @Test
    public void testGetAddress() {
        ExecutorService executor = Executors.newFixedThreadPool(8);
        for (int i = 0; i < 8; i++) {
            executor.execute(() -> {
                geocodeController.getAddress(33.748995f, -84.387982f);
                geocodeController.getAddress(33.772496f, -84.394717f);
                geocodeController.getAddress(33.781109f, -84.386373f);

                geocodeController.getAddress(33.748995f, -84.387982f);
                geocodeController.getAddress(33.772496f, -84.394717f);
                geocodeController.getAddress(33.781109f, -84.386373f);
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) { }

        List<Address> list = cache.list();
        int temp = 42;
    }

    @Test(expected = GeocodingException.class)
    public void testGetAddressWithZeroes() {
        geocodeController.getAddress(0, 0);
    }
}
