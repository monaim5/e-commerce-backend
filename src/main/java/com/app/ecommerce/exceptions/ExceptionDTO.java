package com.app.ecommerce.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
public class ExceptionDTO {
    private final String message;
    private final ZonedDateTime timeStamp;
    private final HttpStatus httpStatus;

    public ExceptionDTO(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timeStamp = ZonedDateTime.now(ZoneId.of("Z"));
    }
}
