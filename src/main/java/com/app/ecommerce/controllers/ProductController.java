package com.app.ecommerce.controllers;

import com.app.ecommerce.models.dtos.CartItemDto;
import com.app.ecommerce.models.dtos.ProductDto;
import com.app.ecommerce.exceptions.AuthorizationException;
import com.app.ecommerce.models.dtos.ResponsePayload;
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
    public ResponseEntity<ResponsePayload<ProductDto>> retrieveProduct(@PathVariable Long id){
        ResponsePayload<ProductDto> responsePayload = new ResponsePayload<>("Product has been retrieved successfully", productService.retrieve(id));
        return ResponseEntity.status(HttpStatus.OK).body(responsePayload);
    }

    @GetMapping
    public ResponseEntity<ResponsePayload<List<ProductDto>>> listProducts(
            @RequestParam(value = "q") Optional<String> nameLike,
            @RequestParam(value = "category") Optional<String> category,
            @RequestParam(value = "page") Optional<Integer> page,
            @RequestParam(value = "pageSize") Optional<Integer> pageSize,
            @RequestParam(value = "sortBy") Optional<String> sortBy
    ) {

        ResponsePayload<List<ProductDto>> responsePayload = new ResponsePayload<>("Products has been retrieved successfully",
                productService.list(nameLike, category, page, pageSize, sortBy));
        return ResponseEntity.status(HttpStatus.OK).body(responsePayload);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePayload<ProductDto>> updateProduct(@PathVariable("id") Long id,
                                                                     @RequestBody ProductDto productDto){
        ResponsePayload<ProductDto> responsePayload = new ResponsePayload<>("Product has been updated successfully", productService.update(id, productDto));
        return ResponseEntity.status(HttpStatus.OK).body(responsePayload);
    }

    @PostMapping
    public ResponseEntity<ResponsePayload<ProductDto>> createProduct(@RequestBody ProductDto productDto) {
        ResponsePayload<ProductDto> responsePayload = new ResponsePayload<>("Product has been created successfully", productService.create(productDto));
        ProductDto newProductDto = productService.create(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responsePayload);
    }

    @PostMapping("/{id}/addToCart")
    public ResponseEntity<ResponsePayload<String>> addToCart(@RequestBody CartItemDto cartItem, @PathVariable String id) {
        ResponsePayload<String> responsePayload = new ResponsePayload<>("Product has been added to cart successfully");
        return ResponseEntity.status(HttpStatus.OK).body(responsePayload);
    }
    @DeleteMapping
    public ResponseEntity<ResponsePayload<Long>> removeProduct(@RequestParam("id") Long id){
        productService.destroy(id);
        ResponsePayload<Long> responsePayload = new ResponsePayload<>("Product has been removed successfully");
        return ResponseEntity.status(HttpStatus.OK).body(responsePayload);
    }
}
