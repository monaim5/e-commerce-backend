/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.mappers;

import com.app.ecommerce.models.entities.PromoType;
import com.app.ecommerce.models.dtos.PromoTypeDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.IterableMapping;
import java.util.List;


@Mapper(componentModel = "spring", uses = {PromoMapper.class, })
public interface PromoTypeMapper {

PromoType toEntity(PromoTypeDto promoTypeDto);

PromoTypeDto toDto(PromoType promoType);

@Named("toFlatPromoTypeDto")
@Mapping(target = "promos", ignore = true)
PromoTypeDto toFlatDto(PromoType promoType);

@IterableMapping(qualifiedByName = "toFlatPromoTypeDto")
List<PromoTypeDto> toDtoList(List<PromoType> promoType);


}