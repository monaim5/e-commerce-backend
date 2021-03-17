package com.app.ecommerce.mappers;

import com.app.ecommerce.dto.PhotoDto;
import com.app.ecommerce.dto.ProductDto;
import com.app.ecommerce.dto.PromoDto;
import com.app.ecommerce.models.Photo;
import com.app.ecommerce.models.Product;
import com.app.ecommerce.models.Promo;
import com.app.ecommerce.models.PromoType;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        uses = {ProductMapper.class})
public interface PromoMapper {

    @Mapping(target = "promoType", ignore = true)
    @Mapping(target = "banners", ignore = true)
    PromoDto toDto(Promo promo);

    @Named("toFlatPromoDto")
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "promoType", ignore = true)
    @Mapping(target = "banners", ignore = true)
    PromoDto toFlatDto(Promo promo);

}
