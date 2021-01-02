package com.app.ecommerce.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
@Data
@ToString
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PhotoDto {
   private Long id;
   private String url;
   private String title;
   private MultipartFile file;
}
