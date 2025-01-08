package com.tka.crickotrack.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle validation errors (for @Valid annotations)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomErrorResponse> handleMethodsArgsNotValidException(MethodArgumentNotValidException ex) {
	    FieldError fieldError = null;
	    String message = null;
	    String fieldName = null;
	    Object rejectedValue = null;
	    
	    if (!ex.getBindingResult().getFieldErrors().isEmpty()) {
	        fieldError = ex.getBindingResult().getFieldErrors().get(0);
	        message = fieldError.getDefaultMessage();
	        fieldName = fieldError.getField();
	        rejectedValue = fieldError.getRejectedValue();
	    }

	    if (fieldError == null) {
	        message = "Validation failed";
	        fieldName = "Unknown field";
	    }

	    CustomErrorResponse errorResponse = new CustomErrorResponse("Validation Error", message, fieldName, rejectedValue);
	    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}


    // Handle invalid format exceptions (e.g., incorrect data type)
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<CustomErrorResponse> handleInvalidFormatException(InvalidFormatException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse("Invalid format", "Invalid format for field: " + ex.getPath().get(0).getFieldName(), ex.getPath().get(0).getFieldName(), ex.getValue());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    // Handle database constraint violation (e.g., unique constraint violation)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<CustomErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse("Data Integrity Violation", ex.getMessage(), null, null);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle resource not found (e.g., when an entity is not found)
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleResourceNotFoundException(NoResourceFoundException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse("Not Found", ex.getMessage(), null, null);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle player not found exception (for specific errors related to players)
    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handlePlayerNotFoundException(PlayerNotFoundException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse("Player Not Found", ex.getMessage(), null, null);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle entity not found exceptions (e.g., entity does not exist in DB)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse("Entity Not Found", ex.getMessage(), null, null);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle bad request errors (e.g., malformed data in the request body)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse("Invalid Argument", ex.getMessage(), null, null);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle runtime exceptions (unexpected errors)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CustomErrorResponse> handleRuntimeException(RuntimeException ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse("Unexpected Error", ex.getMessage(), null, null);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle all exceptions for unexpected errors
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleGenericException(Exception ex) {
        CustomErrorResponse errorResponse = new CustomErrorResponse("Unexpected Error", ex.getMessage(), null, null);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
