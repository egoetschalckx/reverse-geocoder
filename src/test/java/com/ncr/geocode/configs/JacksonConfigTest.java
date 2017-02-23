package com.ncr.geocode.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import static org.junit.Assert.assertNotNull;

public class JacksonConfigTest {

    @Test
    public void testObjectMapper() {
        ObjectMapper objectMapper = new JacksonConfig().objectMapper(new Jackson2ObjectMapperBuilder());

        assertNotNull(objectMapper);
    }
}
