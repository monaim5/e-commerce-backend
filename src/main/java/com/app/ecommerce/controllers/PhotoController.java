package com.app.ecommerce.controllers;

import com.app.ecommerce.exceptions.MonaimException;
import com.app.ecommerce.models.Photo;
import com.app.ecommerce.repositories.PhotoRepository;
import com.app.ecommerce.services.PhotoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.IOException;

@RestController
@RequestMapping
@AllArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping(value = "api/photos/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPhoto(@PathVariable Long id) throws IOException {
        return photoService.getPhoto(id);
    }
}
