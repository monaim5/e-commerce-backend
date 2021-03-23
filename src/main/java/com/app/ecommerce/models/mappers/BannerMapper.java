/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.mappers;

import com.app.ecommerce.models.entities.Banner;
import com.app.ecommerce.models.dtos.BannerDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.IterableMapping;
import java.util.List;


@Mapper(componentModel = "spring", uses = {PromoMapper.class, })
public interface BannerMapper {

Banner toEntity(BannerDto bannerDto);

@Mapping(target = "promo", qualifiedByName = "toFlatPromoDto")
BannerDto toDto(Banner banner);

@Named("toFlatBannerDto")
@Mapping(target = "promo", ignore = true)
BannerDto toFlatDto(Banner banner);

@IterableMapping(qualifiedByName = "toFlatBannerDto")
List<BannerDto> toDtoList(List<Banner> banner);


}