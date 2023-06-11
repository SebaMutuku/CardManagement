package com.logiceacards.api;

import com.logiceacards.dto.ResponseDTO;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import static org.junit.jupiter.api.Assertions.*;



@SpringJUnitConfig
@SpringBootTest
class ControllerAdviceTest {
    @Test
    void testHandledExceptionReturnsUnAuthorized() {
        ControllerAdvice controllerAdvice = new ControllerAdvice();
        ResponseEntity<ResponseDTO> actualHandledExceptionResult = controllerAdvice.handledException(new Exception("foo"));
        assertTrue(actualHandledExceptionResult.hasBody());
        assertTrue(actualHandledExceptionResult.getHeaders().isEmpty());
        assertEquals(401, actualHandledExceptionResult.getStatusCode().value());
        ResponseDTO body = actualHandledExceptionResult.getBody();
        assertNull(body.payload());
        assertEquals("foo", body.message());
        assertEquals(HttpStatus.UNAUTHORIZED, body.status());
    }

    @Test
    void testHandledExceptionReturnsNotFound() {
        ControllerAdvice controllerAdvice = new ControllerAdvice();
        ResponseEntity<ResponseDTO> actualHandledExceptionResult = controllerAdvice
                .handledException(new NoSuchElementException("foo"));
        assertTrue(actualHandledExceptionResult.hasBody());
        assertTrue(actualHandledExceptionResult.getHeaders().isEmpty());
        assertEquals(404, actualHandledExceptionResult.getStatusCode().value());
        ResponseDTO body = actualHandledExceptionResult.getBody();
        assertNull(body.payload());
        assertEquals("foo", body.message());
        assertEquals(HttpStatus.NOT_FOUND, body.status());
    }


    @Test
    void testHandleHttpExceptionsReturnsBadRequest() {
        ControllerAdvice controllerAdvice = new ControllerAdvice();
        ResponseEntity<ResponseDTO> actualHandleHttpExceptionsResult = controllerAdvice
                .handleHttpExceptions(new Exception("foo"));
        assertTrue(actualHandleHttpExceptionsResult.hasBody());
        assertTrue(actualHandleHttpExceptionsResult.getHeaders().isEmpty());
        assertEquals(400, actualHandleHttpExceptionsResult.getStatusCode().value());
        ResponseDTO body = actualHandleHttpExceptionsResult.getBody();
        assertNull(body.payload());
        assertEquals("foo", body.message());
        assertEquals(HttpStatus.BAD_REQUEST, body.status());
    }

    @Test
    void testHandleInternalServerErrors() {
        ControllerAdvice controllerAdvice = new ControllerAdvice();
        ResponseEntity<ResponseDTO> actualHandleInternalServerErrorsResult = controllerAdvice
                .handleInternalServerErrors(new Exception("foo"));
        assertTrue(actualHandleInternalServerErrorsResult.hasBody());
        assertTrue(actualHandleInternalServerErrorsResult.getHeaders().isEmpty());
        assertEquals(500, actualHandleInternalServerErrorsResult.getStatusCodeValue());
        ResponseDTO body = actualHandleInternalServerErrorsResult.getBody();
        assertNull(body.payload());
        assertEquals("foo", body.message());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, body.status());
    }


    @Test
    void testHandleGeneralExceptions() {
        ControllerAdvice controllerAdvice = new ControllerAdvice();
        ResponseEntity<ResponseDTO> actualHandleGeneralExceptionsResult = controllerAdvice
                .handleGeneralExceptions(new Exception("foo"));
        assertTrue(actualHandleGeneralExceptionsResult.hasBody());
        assertTrue(actualHandleGeneralExceptionsResult.getHeaders().isEmpty());
        assertEquals(500, actualHandleGeneralExceptionsResult.getStatusCodeValue());
        ResponseDTO body = actualHandleGeneralExceptionsResult.getBody();
        assertNull(body.payload());
        assertEquals("foo", body.message());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, body.status());
    }

}

