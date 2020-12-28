package com.app.ecommerce.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("api/category")
@RestController
public class CategoryController {

    @GetMapping
    public ResponseEntity<String> getCategories() {
        return new ResponseEntity<>("hello", HttpStatus.OK);
    }
}
