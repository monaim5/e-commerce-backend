package com.app.ecommerce.mappers;

import com.app.ecommerce.dto.PhotoDto;
import com.app.ecommerce.dto.ProductDto;
import com.app.ecommerce.models.Category;
import com.app.ecommerce.models.Photo;
import com.app.ecommerce.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", source = "productDto.id")
    @Mapping(target = "name", source = "productDto.name")
    @Mapping(target = "description", source = "productDto.description")
    @Mapping(target = "category", source = "category")
    Product mapToProduct(ProductDto productDto, Category category);

    @Mapping(target = "photos", expression = "java(mapPhotosToDtos(product.getPhotos()))")
    @Mapping(target = "categoryId", expression = "java(product.getCategory().getId())")
    ProductDto mapToDto(Product product);

    default List<PhotoDto> mapPhotosToDtos(List<Photo> photos) {
        return photos.stream().map(PhotoMapper.INSTANCE::mapToDto).collect(Collectors.toList());
    }
}
