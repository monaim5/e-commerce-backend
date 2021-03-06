package com.app.ecommerce.filters;

import com.app.ecommerce.exceptions.AuthorizationException;
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
        return ResponseEntity.status(exceptionDTO.getHttpStatus()).body(exceptionDTO);
    }

    @ExceptionHandler(InternalException.class)
    public ResponseEntity<ExceptionDTO> handleInternalException(InternalException e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(exceptionDTO.getHttpStatus()).body(exceptionDTO);
    }


//    @ExceptionHandler(ExpiredSessionException.class)
//    public ResponseEntity<ExceptionDTO> handleExpiredSessionException(ExpiredSessionException e) {
//        ExceptionDTO exceptionDTO = new ExceptionDTO(e.getMessage(), HttpStatus.);
//        return ResponseEntity.status(exceptionDTO.getHttpStatus()).body(exceptionDTO);
//    }
}
