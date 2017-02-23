package com.ncr.geocode.configs;

import com.google.maps.GeoApiContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("googlemaps.properties")
public class GoogleMapsConfig {

    @Bean
    public GeoApiContext geoApiContext(@Value("${api.key}") String apiKey) {
        return new GeoApiContext().setApiKey(apiKey);
    }
}
