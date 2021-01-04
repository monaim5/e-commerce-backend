package com.app.ecommerce.controllers;

import com.app.ecommerce.dto.PhotoDto;
import com.app.ecommerce.mappers.PhotoMapper;
import com.app.ecommerce.models.Photo;
import com.app.ecommerce.services.PhotoService;
import com.app.ecommerce.services.PhotoStorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/photos")
public class PhotoController {
    public final static String baseUrl = "http://localhost:8080/api/photos/";
    private final PhotoService photoService;
    private final PhotoStorageService photoStorageService;


    @GetMapping(value = "/{filename}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPhoto(@PathVariable String filename) throws IOException {
        return Files.readAllBytes(photoStorageService.load(filename).getFile().toPath());
    }

    @PostMapping
    public ResponseEntity<PhotoDto> uploadPhoto(@ModelAttribute PhotoDto photoDto) throws IOException {
        Resource resource = photoStorageService.save(photoDto.getFile());
        photoDto = photoService.save(photoDto, resource);
        return ResponseEntity.status(HttpStatus.CREATED).body(photoDto);
    }

    @DeleteMapping
    public void deletePhoto(@RequestBody Long id) {

    }
}
