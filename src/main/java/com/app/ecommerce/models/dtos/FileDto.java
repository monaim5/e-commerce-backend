package com.app.ecommerce.models.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class FileDto {

    @Data
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String url;
        private String filename;
    }

    @Data
    @Builder
    public static class Request {
        private byte[] file;
    }
}
