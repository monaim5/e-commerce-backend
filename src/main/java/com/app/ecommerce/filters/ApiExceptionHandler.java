package com.app.ecommerce.filters;

import com.app.ecommerce.exceptions.AuthorizationException;
import com.app.ecommerce.exceptions.EntityNotFoundException;
import com.app.ecommerce.exceptions.ExceptionDTO;
import com.app.ecommerce.exceptions.InternalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ExceptionDTO> handleAuthorizationException(AuthorizationException e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getMessage(), HttpStatus.UNAUTHORIZED);
        return exceptionDTO.toResponseEntity();
    }

    @ExceptionHandler(InternalException.class)
    public ResponseEntity<ExceptionDTO> handleInternalException(InternalException e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return exceptionDTO.toResponseEntity();
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleEntityNotFoundException(EntityNotFoundException e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getMessage(), HttpStatus.NOT_FOUND);
        return exceptionDTO.toResponseEntity();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handleGlobalException(Exception e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO("Some thing went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        return exceptionDTO.toResponseEntity();
    }

//    @ExceptionHandler(ExpiredSessionException.class)
//    public ResponseEntity<ExceptionDTO> handleExpiredSessionException(ExpiredSessionException e) {
//        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getMessage(), HttpStatus.);
//        return ResponseEntity.status(exceptionDTO.getHttpStatus()).body(exceptionDTO);
//    }
}
