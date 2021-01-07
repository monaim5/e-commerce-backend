package com.app.ecommerce.controllers;

import com.app.ecommerce.dto.PhotoDto;
import com.app.ecommerce.dto.ProductDto;
import com.app.ecommerce.models.Photo;
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
import java.util.Optional;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    private final PhotoService photoService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> retrieveProduct(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.retrieve(id));
    }

//    @GetMapping
//    public ResponseEntity<List<ProductDto>> listProductsByCategory(@RequestParam("category") String categoryName){
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(productService.listByCategory(categoryName));
//    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> listProducts(
            @RequestParam(value = "name") Optional<String> name,
            @RequestParam(value = "category") Optional<String> category,
            @RequestParam(value = "page") Optional<Integer> page,
            @RequestParam(value = "pageSize") Optional<Integer> pageSize,
            @RequestParam(value = "sortBy") Optional<String> sortBy
    ) {
        List<ProductDto> productList = productService.list(name, category, page, pageSize, sortBy);


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
//        System.out.println(productDto.getPhotos());
//        List<PhotoDto> photoDtos = productDto.getPhotos();
//
//        photoDtos = photoService.updatePhotos(photoDtos, newProductDto);
//        newProductDto.setPhotos(photoDtos);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(newProductDto);
    }

    @DeleteMapping
    public ResponseEntity<Long> removeProduct(@RequestParam("id") Long id){
        productService.destroy(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }
}
