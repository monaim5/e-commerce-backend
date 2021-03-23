/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.mappers;

import com.app.ecommerce.models.entities.CartItem;
import com.app.ecommerce.models.dtos.CartItemDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.IterableMapping;
import java.util.List;


@Mapper(componentModel = "spring", uses = {ProductMapper.class, CartMapper.class, })
public interface CartItemMapper {

CartItem toEntity(CartItemDto cartItemDto);

@Mapping(target = "product", qualifiedByName = "toFlatProductDto")
@Mapping(target = "cart", qualifiedByName = "toFlatCartDto")
CartItemDto toDto(CartItem cartItem);

@Named("toFlatCartItemDto")
@Mapping(target = "product", ignore = true)
@Mapping(target = "cart", ignore = true)
CartItemDto toFlatDto(CartItem cartItem);

@IterableMapping(qualifiedByName = "toFlatCartItemDto")
List<CartItemDto> toDtoList(List<CartItem> cartItem);


}