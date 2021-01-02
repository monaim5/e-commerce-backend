package com.app.ecommerce.controllers;

import com.app.ecommerce.dto.PhotoDto;
import com.app.ecommerce.models.Photo;
import com.app.ecommerce.services.PhotoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping
@AllArgsConstructor
@Slf4j
public class PhotoController {

    private final PhotoService photoService;


    @GetMapping(value = "api/photos/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPhoto(@PathVariable Long id) throws IOException {
        return photoService.getPhoto(id);
    }

    @PostMapping(value = "api/photos")
    public ResponseEntity<PhotoDto> uploadPhoto(@ModelAttribute PhotoDto photoDto) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(photoService.save(photoDto));
    }

    @DeleteMapping
    public void deletePhoto(@RequestBody Long id) {

    }
}
