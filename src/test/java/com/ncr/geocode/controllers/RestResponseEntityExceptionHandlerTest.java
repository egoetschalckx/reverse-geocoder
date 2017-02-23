package com.ncr.geocode.controllers;

import com.ncr.geocode.models.Error;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class RestResponseEntityExceptionHandlerTest {

    RestResponseEntityExceptionHandler restResponseEntityExceptionHandler = new RestResponseEntityExceptionHandler();

    @Test
    public void testHandleGeocodingException() {
        String msg = "foo";

        ResponseEntity<Object> responseEntity = restResponseEntityExceptionHandler.handleGeocodingException(
                new RuntimeException(msg),
                mock(WebRequest.class));

        assertThat(responseEntity.getBody(), instanceOf(Error.class));
        assertEquals(msg, ((Error)responseEntity.getBody()).getMsg());
    }
}
