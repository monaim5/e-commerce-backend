package com.app.ecommerce.dto;

import com.app.ecommerce.models.Category;
import com.app.ecommerce.models.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private String designation;
    private String description;
    private float price;
    private int quantity;
    private Long categoryId;
    private Collection<PhotoDto> photos;
}
