/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.mappers;

import com.app.ecommerce.models.entities.Category;
import com.app.ecommerce.models.dtos.CategoryDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.IterableMapping;
import java.util.List;


@Mapper(componentModel = "spring", uses = {ProductMapper.class, })
public interface CategoryMapper {

Category toEntity(CategoryDto categoryDto);

CategoryDto toDto(Category category);

@Named("toFlatCategoryDto")
@Mapping(target = "product", ignore = true)
CategoryDto toFlatDto(Category category);

@IterableMapping(qualifiedByName = "toFlatCategoryDto")
List<CategoryDto> toDtoList(List<Category> category);


}