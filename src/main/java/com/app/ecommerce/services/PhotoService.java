package com.app.ecommerce.services;

import com.app.ecommerce.dto.PhotoDto;
import com.app.ecommerce.exceptions.MonaimException;
import com.app.ecommerce.mappers.PhotoMapper;
import com.app.ecommerce.models.Category;
import com.app.ecommerce.models.Photo;
import com.app.ecommerce.models.Product;
import com.app.ecommerce.repositories.PhotoRepository;
import com.app.ecommerce.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final ProductRepository productRepository;
    private final PhotoMapper photoMapper;
    private static final Path productPhotos = Paths.get(System.getProperty("user.home"), "Documents/ecom assets/products/");
    private final String getPhotoUrl = "http://localhost:8080/api/photos/";

    public byte[] getPhoto(@PathVariable Long id) throws IOException {
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new MonaimException("no such photo"));
        return Files.readAllBytes(Paths.get(photo.getPath()));
    }

    public PhotoDto update(PhotoDto photoDto) {
        Photo photo = photoRepository.findById(photoDto.getId())
                .orElseThrow(() -> new MonaimException("Photo not found for this id :: " + photoDto.getId()));
        updatePhotoFromDto(photo, photoDto);
        photoRepository.save(photo);
        return photoDto;
    }

    private void updatePhotoFromDto(Photo photo, PhotoDto photoDto) {
        Product product = productRepository.findById(photoDto.getProductId())
                .orElseThrow(() -> new MonaimException("no such category"));
        photo.setProduct(product);
    }

    public PhotoDto save(PhotoDto photoDto, Resource resource) throws IOException {
        Photo photo = photoRepository.save(photoMapper.mapToPhoto(photoDto, resource));
        return photoMapper.mapToDto(photo);
    }

    private Photo mapToPhoto(PhotoDto photoDto) {
        Long photoId = photoDto.getId();


        Product product;
        if(photoDto.getProductId() != null){
            Optional<Product> TestProduct = productRepository.findById(photoDto.getProductId());
            product = TestProduct.orElse(null);
        } else {
            product = null;
        }
        return Photo.builder()
                .product(product)
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
