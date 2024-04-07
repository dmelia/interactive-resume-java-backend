package com.interactiveresume.Interactive.Resume.Backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Global exception handling
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnauthorizedAccessException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleUnauthorizedAccessException(UnauthorizedAccessException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.toString()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InputInvalidException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleUnauthorizedAccessException(InputInvalidException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.toString()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResumeNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleResumeNotFoundException(ResumeNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.toString()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResumePageNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleResumePageNotFoundException(ResumePageNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.toString()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SectionNotFoundException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleSectionNotFoundException(SectionNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.toString()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Data
    @AllArgsConstructor
    public static class ErrorResponse {
        private String message;
        private String errorCode;
    }
}

