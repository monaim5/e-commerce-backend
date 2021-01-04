package com.app.ecommerce.mappers;

import com.app.ecommerce.controllers.PhotoController;
import com.app.ecommerce.dto.PhotoDto;
import com.app.ecommerce.models.Photo;
import com.app.ecommerce.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Mapper(componentModel = "spring")
public interface PhotoMapper {
    PhotoMapper INSTANCE = Mappers.getMapper(PhotoMapper.class);

    @Mapping(target = "path", ignore = true)
    @Mapping(target = "id", source = "photoDto.id")
    Photo mapToPhoto(PhotoDto photoDto, Product product);

    @Mapping(target = "path", expression = "java(getUri(resource))")
    @Mapping(target = "url", expression = "java(getUrl(resource))")
    @Mapping(target = "product", ignore = true)
    Photo mapToPhoto(PhotoDto photoDto, Resource resource);

//    @Mapping(target = "productId", expression = "java(mapToProductId(photo))")
    @Mapping(target = "file", ignore = true)
    PhotoDto mapToDto(Photo photo);


//    default Long mapToProductId(Photo photo){
//        System.out.println(photo.getProduct());
//        return (photo.getProduct() == null) ? null : photo.getProduct().getId();
//    }

    default String getUrl(Resource resource) {
        String baseUrl = PhotoController.baseUrl;
        return baseUrl + resource.getFilename();
    }

    default String getUri(Resource resource) {
        try {
            return resource.getURI().getPath();
        } catch (IOException e) {
            return null;
        }
    }

//    default Product mapToProduct(PhotoDto photoDto){
//        return photoDto.getProduct().getId();
//    }
}
