package com.app.ecommerce.dto;

import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoDto {
   private Long id;
   private String url;
   private String title;
   private Long productId;
   private MultipartFile file;
}
