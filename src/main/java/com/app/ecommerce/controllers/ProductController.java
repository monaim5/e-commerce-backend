package com.app.ecommerce.controllers;

import com.app.ecommerce.dto.CartItemDto;
import com.app.ecommerce.dto.ProductDto;
import com.app.ecommerce.exceptions.AuthorizationException;
import com.app.ecommerce.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> retrieveProduct(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.retrieve(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> listProducts(
            @RequestParam(value = "q") Optional<String> nameLike,
            @RequestParam(value = "category") Optional<String> category,
            @RequestParam(value = "page") Optional<Integer> page,
            @RequestParam(value = "pageSize") Optional<Integer> pageSize,
            @RequestParam(value = "sortBy") Optional<String> sortBy
    ) {
        List<ProductDto> productList = productService.list(nameLike, category, page, pageSize, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto)
            throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.update(id, productDto));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) throws IOException {
        ProductDto newProductDto = productService.create(productDto);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(newProductDto);
    }

    @PostMapping("/{id}/addToCart")
    public ResponseEntity<String> addToCart(@RequestBody CartItemDto cartItem, @PathVariable String id) {

        return ResponseEntity.status(HttpStatus.OK).body("added");
    }
    @DeleteMapping
    public ResponseEntity<Long> removeProduct(@RequestParam("id") Long id){
        productService.destroy(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}
