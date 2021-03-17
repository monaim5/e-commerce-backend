package com.app.ecommerce.mappers;

import com.app.ecommerce.dto.ProductDto;
import com.app.ecommerce.models.Category;
import com.app.ecommerce.models.Product;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {PhotoMapper.class, PromoMapper.class})
public interface ProductMapper {

    @Mapping(target = "promo", qualifiedByName = "toFlatPromoDto")
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "cartItems", ignore = true)
    ProductDto toDto(Product product);

    @Named("toFlatProductDto")
    @Mapping(target = "photos", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "promo", ignore = true)
    @Mapping(target = "cartItems", ignore = true)
    ProductDto toFlatDto(Product product);

    @IterableMapping(qualifiedByName = "toFlatProductDto")
    List<ProductDto> toDtoList(List<Product> products);

}
