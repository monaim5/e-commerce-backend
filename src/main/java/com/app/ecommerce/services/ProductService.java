package com.app.ecommerce.services;


import com.app.ecommerce.dto.PhotoDto;
import com.app.ecommerce.dto.ProductDto;
import com.app.ecommerce.exceptions.MonaimException;
import com.app.ecommerce.models.Category;
import com.app.ecommerce.models.Photo;
import com.app.ecommerce.models.Product;
import com.app.ecommerce.repositories.CategoryRepository;
import com.app.ecommerce.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public ProductDto save(ProductDto productDto){
        Product product = productRepository.save(mapToProduct(productDto));
        productDto.setId(product.getId());
        return productDto;
    }

    @Transactional(readOnly = true)
    public List<ProductDto> getAll(){
        return productRepository.findAll()
            .stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    public Product mapToProduct(ProductDto productDto){
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new MonaimException("no such category"));
        return Product.builder()
                .name(productDto.getName())
                .designation(productDto.getDesignation())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .category(category)
                .build();
    }

    private ProductDto mapToDto(Product product) {
        List<PhotoDto> photos = product.getPhotos().stream().map(
                PhotoService::mapToDto).collect(Collectors.toList());

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .designation(product.getDesignation())
                .categoryId(product.getCategory().getId())
                .photos(photos)
                .build();
    }

}
