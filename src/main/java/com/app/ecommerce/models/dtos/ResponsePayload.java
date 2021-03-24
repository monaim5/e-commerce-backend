package com.app.ecommerce.models.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponsePayload<T> {
    private String message;
    private T data;

    public ResponsePayload(String message, T data) {
        this.message = message;
        this.data = data;
    }
}
