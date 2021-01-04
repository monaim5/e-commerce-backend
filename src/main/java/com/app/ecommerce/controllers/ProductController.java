package com.app.ecommerce.controllers;

import com.app.ecommerce.dto.PhotoDto;
import com.app.ecommerce.dto.ProductDto;
import com.app.ecommerce.models.Product;
import com.app.ecommerce.services.PhotoService;
import com.app.ecommerce.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final PhotoService photoService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProduct(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> editProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto)
            throws ResourceNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.edit(id, productDto));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) throws IOException {
        ProductDto newProductDto = productService.save(productDto);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(newProductDto);
    }

    @DeleteMapping
    public ResponseEntity<Long> deleteProduct(@RequestParam("id") Long id){
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}
