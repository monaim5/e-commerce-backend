package com.app.ecommerce.models.dtos;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

public class FileDto {

    @Data
    @Builder
    public static class Response {
        private Long id;
        private String url;
        private String filename;
    }

    @Data
    @Builder
    public static class Request {
        private MultipartFile file;
    }
}
