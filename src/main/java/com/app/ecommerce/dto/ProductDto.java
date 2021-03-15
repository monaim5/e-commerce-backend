package com.app.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String title;
    private String designation;
    private String description;
    private boolean available;
    private float price;
    private float rate;
    private int sales;
    private int quantity;
    private Long categoryId;
    private List<PhotoDto> photos;
}
