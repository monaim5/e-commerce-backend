package com.app.ecommerce.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PromoTypeDto {
    private String name;
    private String description;
}
