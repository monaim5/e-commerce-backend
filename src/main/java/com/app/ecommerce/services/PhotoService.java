package com.app.ecommerce.services;

import com.app.ecommerce.dto.PhotoDto;
import com.app.ecommerce.exceptions.MonaimException;
import com.app.ecommerce.models.Photo;
import com.app.ecommerce.repositories.PhotoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
@Slf4j
public class PhotoService {

    private final PhotoRepository photoRepository;
    private static final Path productPhotos = Paths.get(System.getProperty("user.home"), "Documents/ecom assets/products/");
    private final String getPhotoUrl = "http://localhost:8080/api/photos/";

    public byte[] getPhoto(@PathVariable Long id) throws IOException {
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new MonaimException("no such photo"));
        return Files.readAllBytes(Paths.get(photo.getPath()));
    }

    public PhotoDto save(PhotoDto photoDto) throws IOException {
        Path path = Paths.get(productPhotos.toString(), photoDto.getTitle() + ".jpg");
        photoDto.getFile().transferTo(new File(path.toString()));
        Photo photo = photoRepository.save(mapToPhoto(photoDto));
        photo.setUrl(getPhotoUrl + photo.getId());
        photoDto.setId(photo.getId());
        photoDto.setUrl(photo.getUrl());
        photoDto.setFile(null);
        photoDto.setUrl(photo.getUrl());
        return photoDto;
    }

    private static Photo mapToPhoto(PhotoDto photoDto) {
        return Photo.builder()
                .title(photoDto.getTitle())
                .path(Paths.get(productPhotos.toString(), photoDto.getTitle() + ".jpg").toString())
                .build();
    }

    static PhotoDto mapToDto(Photo photo) {
        return PhotoDto.builder()
                .id(photo.getId())
                .url(photo.getUrl())
                .title(photo.getTitle())
                .build();
    }
}
