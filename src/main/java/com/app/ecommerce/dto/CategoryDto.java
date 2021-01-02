package com.app.ecommerce.dto;

import com.app.ecommerce.models.Product;
import lombok.Builder;
import lombok.Data;

import javax.persistence.OneToMany;
import java.util.Collection;

@Builder
@Data
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private String icon;
    private String photo;
}
