package com.app.ecommerce.services;


import com.app.ecommerce.dto.PhotoDto;
import com.app.ecommerce.dto.ProductDto;
import com.app.ecommerce.exceptions.MonaimException;
import com.app.ecommerce.mappers.ProductMapper;
import com.app.ecommerce.models.Category;
import com.app.ecommerce.models.Photo;
import com.app.ecommerce.models.Product;
import com.app.ecommerce.repositories.CategoryRepository;
import com.app.ecommerce.repositories.PhotoRepository;
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
    private final PhotoRepository photoRepository;
    private final ProductMapper productMapper;

    @Transactional
    public ProductDto save(ProductDto productDto){
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new MonaimException("no such category"));

        List<Photo> photos = productDto.getPhotos().stream().map(
                (dto) -> photoRepository.findById(dto.getId()).orElseThrow(() -> new MonaimException("cant get photo"))
        ).collect(Collectors.toList());

        Product product = productRepository.save(productMapper.mapToProduct(productDto, category));

        photos.forEach(photo -> {
            photo.setProduct(product);
            photoRepository.save(photo);
        });

        return productMapper.mapToDto(product);
    }

    @Transactional
    public ProductDto edit(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new MonaimException("Product not found for this id :: " + id));
        updateProductFromDto(product, productDto);
        productRepository.save(product);
        return productDto;
    }

    public ProductDto get(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new MonaimException("Product not found for this id :: " + id));
        return mapToDto(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ProductDto> getAll(){
        return productRepository.findAll()
            .stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    private void updateProductFromDto(Product product, ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new MonaimException("no such category"));
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setDesignation(productDto.getDesignation());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setCategory(category);
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
