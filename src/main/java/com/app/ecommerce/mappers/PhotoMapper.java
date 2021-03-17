package com.app.ecommerce.mappers;

import com.app.ecommerce.controllers.PhotoController;
import com.app.ecommerce.dto.PhotoDto;
import com.app.ecommerce.dto.ProductDto;
import com.app.ecommerce.models.Photo;
import com.app.ecommerce.models.Product;
import lombok.AllArgsConstructor;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

@Mapper(uses = {ProductMapper.class}, componentModel = "spring")
public interface PhotoMapper {

    @Mapping(target = "product", qualifiedByName = {"toFlatProductDto"})
    @Mapping(target = "promo", ignore = true)
    PhotoDto toDto(Photo photo);

    @Named("toFlatPhotoDto")
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "promo", ignore = true)
    PhotoDto toFlatDto(Photo photo);

    @IterableMapping(qualifiedByName = "toFlatPhotoDto")
    List<PhotoDto> toFlatDtos(List<Photo> photos);

}
