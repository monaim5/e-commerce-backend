package com.app.ecommerce.services;


import com.app.ecommerce.dto.ProductDto;
import com.app.ecommerce.models.Photo;
import com.app.ecommerce.models.Product;
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
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Product save(ProductDto productDto){
        Product product = productRepository.save(mapProductDto(productDto));
        productDto.setId(product.getId());
        return product;
    }

    @Transactional(readOnly = true)
    public List<ProductDto> getAll(){
        return productRepository.findAll()
            .stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    public Product mapProductDto(ProductDto productDto){
        return Product.builder()
                .name(productDto.getName())
                .designation(productDto.getDesignation())
                .build();
    }

    private ProductDto mapToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .designation(product.getDesignation())
                .category(product.getCategory().getName())
                .photos(product.getPhotos().stream().map(
                        (Photo photo) -> photo.getUrl())
                        .collect(Collectors.toList()))
                .build();
    }

}
