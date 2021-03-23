/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.mappers;

import com.app.ecommerce.models.entities.Promo;
import com.app.ecommerce.models.dtos.PromoDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.IterableMapping;
import java.util.List;


@Mapper(componentModel = "spring", uses = {ProductMapper.class, PromoTypeMapper.class, BannerMapper.class, })
public interface PromoMapper {

Promo toEntity(PromoDto promoDto);

@Mapping(target = "promoType", qualifiedByName = "toFlatPromoTypeDto")
PromoDto toDto(Promo promo);

@Named("toFlatPromoDto")
@Mapping(target = "products", ignore = true)
@Mapping(target = "promoType", ignore = true)
@Mapping(target = "banners", ignore = true)
PromoDto toFlatDto(Promo promo);

@IterableMapping(qualifiedByName = "toFlatPromoDto")
List<PromoDto> toDtoList(List<Promo> promo);


}