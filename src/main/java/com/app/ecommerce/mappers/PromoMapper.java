package com.app.ecommerce.mappers;

import com.app.ecommerce.dto.PhotoDto;
import com.app.ecommerce.dto.ProductDto;
import com.app.ecommerce.dto.PromoDto;
import com.app.ecommerce.models.Photo;
import com.app.ecommerce.models.Product;
import com.app.ecommerce.models.Promo;
import com.app.ecommerce.models.PromoType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PromoMapper {

    @Mapping(target = "products", expression = "java(mapProductsToDtos(promo.getProducts()))")
    @Mapping(target = "promoType", expression = "java(promo.getPromoType().getName())")
    @Mapping(target = "banners", expression = "java(mapPhotosToDtos(promo.getBanners()))")
    PromoDto mapToDto(Promo promo);

    @Mapping(target = "promoType", source = "promoType")
    @Mapping(target = "banners", ignore = true)
    Promo mapToPromo(PromoDto promoDto, PromoType promoType);

    default List<ProductDto> mapProductsToDtos(List<Product> products) {
        return products.stream().map(ProductMapper.INSTANCE::mapToDto).collect(Collectors.toList());
    }

    default List<PhotoDto> mapPhotosToDtos(List<Photo> banners) {
        return banners.stream().map(PhotoMapper.INSTANCE::mapToDto).collect(Collectors.toList());
    }
}
