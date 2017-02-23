package com.ncr.geocode.configs;

import com.ncr.geocode.cache.Cache;
import com.ncr.geocode.cache.LinkedHashMapCache;
import com.ncr.geocode.models.Address;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("cache.properties")
public class AddressCacheConfig {

    @Bean
    public Cache<String, Address> addressCache(@Value("${cache.size}") int size) {
        return new LinkedHashMapCache<>(size);
    }
}
