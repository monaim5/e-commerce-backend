package com.app.ecommerce.dto;

import com.app.ecommerce.models.Category;
import com.app.ecommerce.models.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.util.Collection;
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
