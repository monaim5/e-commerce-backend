package com.app.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class PromoDto {
    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private float discountAmount;
    private boolean active;
    private String promoType;
    private List<ProductDto> products;
    private List<PhotoDto> banners;
}
