package com.app.ecommerce.services;

import com.app.ecommerce.dto.PhotoDto;
import com.app.ecommerce.dto.ProductDto;
import com.app.ecommerce.exceptions.MonaimException;
import com.app.ecommerce.mappers.PhotoMapper;
import com.app.ecommerce.models.Photo;
import com.app.ecommerce.models.Product;
import com.app.ecommerce.repositories.PhotoRepository;
import com.app.ecommerce.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class PhotoService {

    private final PhotoRepository photoRepository;
    private final ProductRepository productRepository;
    private final PhotoMapper photoMapper;
    private static final Path productPhotos = Paths.get(System.getProperty("user.home"), "Documents/ecom assets/products/");
    private final String getPhotoUrl = "http://localhost:8080/api/photos/";

    public PhotoDto create(PhotoDto photoDto, Resource resource) throws IOException {
        Photo photo = photoRepository.save(photoMapper.mapToPhoto(photoDto, resource));
        return photoMapper.mapToDto(photo);
    }

    public List<PhotoDto> updatePhotos(List<PhotoDto> photoDtos, ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).orElseThrow(() -> new MonaimException("cant get product"));
        return photoDtos.stream().map(photoDto -> {
            Photo photo = photoRepository.findById(photoDto.getId()).orElseThrow(() -> new MonaimException("cant get photo"));
            photo.setProduct(product);
            return photoMapper.mapToDto(photoRepository.save(photo));
        }).collect(Collectors.toList());
    }

//    public byte[] getPhoto(@PathVariable Long id) throws IOException {
//        Photo photo = photoRepository.findById(id).orElseThrow(() -> new MonaimException("no such photo"));
//        return Files.readAllBytes(Paths.get(photo.getPath()));
//    }

//    public PhotoDto update(PhotoDto photoDto) {
//        Photo photo = photoRepository.findById(photoDto.getId())
//                .orElseThrow(() -> new MonaimException("Photo not found for this id :: " + photoDto.getId()));
//        updatePhotoFromDto(photo, photoDto);
//        photoRepository.save(photo);
//        return photoDto;
//    }

//    private void updatePhotoFromDto(Photo photo, PhotoDto photoDto) {
//        Product product = productRepository.findById(photoDto.getId())
//                .orElseThrow(() -> new MonaimException("no such category"));
//        photo.setProduct(product);
//    }

//    private Photo mapToPhoto(PhotoDto photoDto) {
//        Long photoId = photoDto.getId();
//
//
//        Product product;
//        if(photoDto.getProductId() != null){
//            Optional<Product> TestProduct = productRepository.findById(photoDto.getProductId());
//            product = TestProduct.orElse(null);
//        } else {
//            product = null;
//        }
//        return Photo.builder()
//                .product(product)
//                .title(photoDto.getTitle())
//                .path(Paths.get(productPhotos.toString(), photoDto.getTitle() + ".jpg").toString())
//                .build();
//    }

//    static PhotoDto mapToDto(Photo photo) {
//        return PhotoDto.builder()
//                .id(photo.getId())
//                .url(photo.getUrl())
//                .title(photo.getTitle())
//                .build();
//    }

}
