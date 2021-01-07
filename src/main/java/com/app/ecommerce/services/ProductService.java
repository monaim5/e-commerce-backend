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
import com.app.ecommerce.specifications.ProductSpecification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
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
    public ProductDto create(ProductDto productDto){
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new MonaimException("no such category"));

        List<Long> photoIds = productDto.getPhotos().stream().map(PhotoDto::getId).collect(Collectors.toList());
        Product product = productRepository.save(productMapper.mapToProduct(productDto, category));
        photoRepository.updateProduct(photoIds, product.getId());
        productDto.setId(product.getId());
        return productDto;
    }

    @Transactional
    public ProductDto update(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new MonaimException("Product not found for this id :: " + id));
        updateProductFromDto(product, productDto);
        productRepository.save(product);
        return productDto;
    }

    public ProductDto retrieve(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new MonaimException("Product not found for this id :: " + id));
        return productMapper.mapToDto(product);
    }

    @Transactional
    public void destroy(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ProductDto> list(){
        return productRepository.findAll()
            .stream()
            .map(productMapper::mapToDto)
            .collect(Collectors.toList());
    }

    @Transactional
    public List<ProductDto> list(Optional<String> name,
                              Optional<String> categoryName,
                              Optional<Integer> page,
                              Optional<Integer> pageSize,
                              Optional<String> sortBy) {

        Specification<Product> nameSpec = ProductSpecification.nameLike(name.orElse(null));
        Specification<Product> categorySpec = ProductSpecification.categoryEquals(categoryName.orElse(null));

        Specification<Product> specification = Specification.where(nameSpec).and(categorySpec);
        return productRepository.findAll(
                specification,
                PageRequest.of(page.orElse(0), pageSize.orElse(50),
                        Sort.Direction.DESC, sortBy.orElse("id")))
                .getContent()
                .stream().map(productMapper::mapToDto).collect(Collectors.toList());
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

    public List<ProductDto> listByCategory(String categoryName) {
        Category category = categoryRepository.findByNameEquals(categoryName);
        List<Product> products = productRepository.findAllByCategory(category);
        return products.stream().map(productMapper::mapToDto).collect(Collectors.toList());
    }


//    public Product mapToProduct(ProductDto productDto){
//        Category category = categoryRepository.findById(productDto.getCategoryId())
//                .orElseThrow(() -> new MonaimException("no such category"));
//        return Product.builder()
//                .name(productDto.getName())
//                .designation(productDto.getDesignation())
//                .description(productDto.getDescription())
//                .price(productDto.getPrice())
//                .quantity(productDto.getQuantity())
//                .category(category)
//                .build();
//    }

//    private ProductDto mapToDto(Product product) {
//        List<PhotoDto> photos = product.getPhotos().stream().map(
//                PhotoService::mapToDto).collect(Collectors.toList());
//
//        return ProductDto.builder()
//                .id(product.getId())
//                .name(product.getName())
//                .designation(product.getDesignation())
//                .categoryId(product.getCategory().getId())
//                .photos(photos)
//                .build();
//    }
}
