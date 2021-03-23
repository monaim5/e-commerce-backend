/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.mappers;

import com.app.ecommerce.models.entities.Product;
import com.app.ecommerce.models.dtos.ProductDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.IterableMapping;
import java.util.List;


@Mapper(componentModel = "spring", uses = {PhotoMapper.class, CartItemMapper.class, OrderItemMapper.class, CategoryMapper.class, PromoMapper.class, })
public interface ProductMapper {

Product toEntity(ProductDto productDto);

@Mapping(target = "category", qualifiedByName = "toFlatCategoryDto")
@Mapping(target = "promo", qualifiedByName = "toFlatPromoDto")
ProductDto toDto(Product product);

@Named("toFlatProductDto")
@Mapping(target = "photos", ignore = true)
@Mapping(target = "cartItems", ignore = true)
@Mapping(target = "orderItems", ignore = true)
@Mapping(target = "category", ignore = true)
@Mapping(target = "promo", ignore = true)
ProductDto toFlatDto(Product product);

@IterableMapping(qualifiedByName = "toFlatProductDto")
List<ProductDto> toDtoList(List<Product> product);


}