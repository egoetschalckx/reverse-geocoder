package com.ncr.geocode.controllers;

import com.ncr.geocode.models.Error;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RestResponseEntityExceptionHandlerTest {

    RestResponseEntityExceptionHandler restResponseEntityExceptionHandler = new RestResponseEntityExceptionHandler();

    @Test
    public void testHandleGeocodingException() {
        String msg = "foo";

        ResponseEntity<Object> responseEntity = restResponseEntityExceptionHandler.handleGeocodingException(
                new RuntimeException(msg),
                mock(WebRequest.class));

        assertThat(responseEntity.getBody(), instanceOf(Error.class));
        assertEquals(msg, ((Error)responseEntity.getBody()).getErrors().get(0));
    }

    @Test
    public void testHandleMethodArgumentNotValid() {
        FieldError fieldError = new FieldError("foo", "bar", "baz");
        List<FieldError> fieldErrors = asList(fieldError);
        ObjectError objectError = new ObjectError("foo", "bar");
        List<ObjectError> objectErrors = asList(objectError);
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);
        when(bindingResult.getGlobalErrors()).thenReturn(objectErrors);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<Object> responseEntity = restResponseEntityExceptionHandler.handleMethodArgumentNotValid(ex, null, null, null);

        Error error = (Error) responseEntity.getBody();
        assertEquals("bar: baz", error.getErrors().get(0));
        assertEquals("foo: bar", error.getErrors().get(1));
    }

    @Test
    public void testHandleMissingServletRequestParameter() {

        MissingServletRequestParameterException ex = new MissingServletRequestParameterException("foo", "bar");

        ResponseEntity<Object> responseEntity = restResponseEntityExceptionHandler.handleMissingServletRequestParameter(ex, null, null, null);

        Error error = (Error) responseEntity.getBody();
        assertEquals("foo parameter is missing", error.getErrors().get(0));
    }

    /*@Test
    public void testHandleConstraintViolation() {
        ConstraintViolationException ex = mock(ConstraintViolationException.class);
        Set<ConstraintViolation<?>> constraintViolations = new HashSet<>();
        ConstraintViolation<String> constraintViolation = mock(ConstraintViolation.class);
        when(constraintViolation.getRootBeanClass()).thenReturn(String.class);
        Path path = mock(Path.class);
        when(path.toString()).thenReturn("bar");
        when(constraintViolation.getPropertyPath()).thenReturn(path);
        when(constraintViolation.getMessage()).thenReturn("foo");
        constraintViolations.add(constraintViolation);
        when(ex.getConstraintViolations()).thenReturn(constraintViolations);

        ResponseEntity<Object> responseEntity = restResponseEntityExceptionHandler.handleConstraintViolation(ex, null);

        Error error = (Error) responseEntity.getBody();
        assertEquals(String.class.getName() + " bar: foo", error.getErrors().get(0));
    }*/

    @Test
    public void testHandleMethodArgumentTypeMismatch() {
        MethodArgumentTypeMismatchException ex = mock(MethodArgumentTypeMismatchException.class);
        when(ex.getName()).thenReturn("foo");
        doReturn(String.class).when(ex).getRequiredType();
        ResponseEntity<Object> responseEntity = restResponseEntityExceptionHandler.handleMethodArgumentTypeMismatch(ex, null);

        Error error = (Error) responseEntity.getBody();
        assertEquals("foo should be of type " + String.class.getName(), error.getErrors().get(0));

        //ex.getName() + " should be of type " + ex.getRequiredType().getName();
    }
}
