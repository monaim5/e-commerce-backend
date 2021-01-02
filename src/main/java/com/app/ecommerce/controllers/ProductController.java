package com.app.ecommerce.controllers;

import com.app.ecommerce.dto.ProductDto;
import com.app.ecommerce.models.Product;
import com.app.ecommerce.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(productService.save(productDto));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProduct(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getAll());
    }

//    @GetMapping("photos/{productId}/{imageId}")
//    public byte[] getPhoto(@PathVariable Long id){
//
//        return null;
//    }

}
