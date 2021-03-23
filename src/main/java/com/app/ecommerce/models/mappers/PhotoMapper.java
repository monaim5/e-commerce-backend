/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.mappers;

import com.app.ecommerce.models.entities.Photo;
import com.app.ecommerce.models.dtos.PhotoDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.IterableMapping;
import java.util.List;


@Mapper(componentModel = "spring", uses = {ProductMapper.class, })
public interface PhotoMapper {

Photo toEntity(PhotoDto photoDto);

@Mapping(target = "product", qualifiedByName = "toFlatProductDto")
PhotoDto toDto(Photo photo);

@Named("toFlatPhotoDto")
@Mapping(target = "product", ignore = true)
PhotoDto toFlatDto(Photo photo);

@IterableMapping(qualifiedByName = "toFlatPhotoDto")
List<PhotoDto> toDtoList(List<Photo> photo);


}