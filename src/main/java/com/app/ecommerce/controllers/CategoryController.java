package com.app.ecommerce.controllers;

import com.app.ecommerce.models.dtos.CategoryDto;
import com.app.ecommerce.models.dtos.ResponsePayload;
import com.app.ecommerce.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("api/category")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ResponsePayload<List<CategoryDto>>> getCategories() {
        ResponsePayload<List<CategoryDto>> responsePayload = new ResponsePayload<>("categoryService.getAll()", categoryService.getAll());
        return ResponseEntity.status(HttpStatus.OK).body(responsePayload);
    }

    @PostMapping
    public ResponseEntity<ResponsePayload<CategoryDto>> createCategory(@RequestBody CategoryDto categoryDto) {
        ResponsePayload<CategoryDto> responsePayload = new ResponsePayload<>("Category has been created successfully", categoryService.save(categoryDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(responsePayload);
    }
}
