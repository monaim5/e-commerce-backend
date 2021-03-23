package com.app.ecommerce.controllers;

import com.app.ecommerce.models.dtos.FileDto;
import com.app.ecommerce.models.dtos.PhotoDto;
import com.app.ecommerce.services.PhotoService;
import com.app.ecommerce.services.PhotoStorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/photos")
public class PhotoController {
    public final static String baseUrl = "http://localhost:8080/api/photos/";
    private final PhotoService photoService;
    private final PhotoStorageService photoStorageService;
    private final RestTemplate restTemplate;

    private static final String STORAGE_SERVICE_ENDPOINT = "http://localhost:8081";


    @GetMapping(value = "/{filename}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPhoto(@PathVariable String filename) throws IOException {
        return Files.readAllBytes(photoStorageService.load(filename).getFile().toPath());
    }

    @PostMapping
    public ResponseEntity<FileDto.Response> uploadPhoto(@ModelAttribute FileDto.Request fileDto) throws IOException {
        ResponseEntity<FileDto.Response> response = restTemplate.postForEntity(STORAGE_SERVICE_ENDPOINT, fileDto, FileDto.Response.class);
        FileDto.Response savedFile = response.getBody();
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFile);
//        Resource resource = photoStorageService.save(photoDto.getFile());
//        photoDto = photoService.create(photoDto, resource);
//        return ResponseEntity.status(HttpStatus.CREATED).body(photoDto);
    }

    @DeleteMapping
    public void deletePhoto(@RequestBody Long id) {

    }
}
