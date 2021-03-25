package com.app.ecommerce.controllers;

import com.app.ecommerce.models.dtos.FileDto;
import com.app.ecommerce.models.dtos.ResponsePayload;
import com.app.ecommerce.services.PhotoService;
import com.app.ecommerce.services.PhotoStorageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.resource.ResourceUrlProviderExposingInterceptor;

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

    @PostMapping("/upload")
    public ResponseEntity<ResponsePayload<FileDto.Response>> uploadPhoto(@RequestParam("file") MultipartFile file) throws IOException {
        log.info(String.valueOf(file));
        FileDto.Request fileReq = FileDto.Request.builder().file(file).build();
        ResponseEntity<FileDto.Response> response = restTemplate.postForEntity(STORAGE_SERVICE_ENDPOINT, fileReq, FileDto.Response.class);
        FileDto.Response savedFile = response.getBody();
        ResponsePayload<FileDto.Response> responsePayload = new ResponsePayload<>("Photo has been uploaded successfully", savedFile);
        return ResponseEntity.status(HttpStatus.CREATED).body(responsePayload);
//        Resource resource = photoStorageService.save(photoDto.getFile());
//        photoDto = photoService.create(photoDto, resource);
//        return ResponseEntity.status(HttpStatus.CREATED).body(photoDto);
    }

    @DeleteMapping
    public ResponseEntity<ResponsePayload<Long>> deletePhoto(@RequestBody Long id) {
        ResponsePayload<Long> responsePayload = new ResponsePayload<>("must define this method", id);
        return ResponseEntity.status(HttpStatus.OK).body(responsePayload);
    }
}
