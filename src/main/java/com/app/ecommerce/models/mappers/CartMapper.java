/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.mappers;

import com.app.ecommerce.models.entities.Cart;
import com.app.ecommerce.models.dtos.CartDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.IterableMapping;
import java.util.List;


@Mapper(componentModel = "spring", uses = {UserMapper.class, CartItemMapper.class, })
public interface CartMapper {

Cart toEntity(CartDto cartDto);

@Mapping(target = "user", qualifiedByName = "toFlatUserDto")
CartDto toDto(Cart cart);

@Named("toFlatCartDto")
@Mapping(target = "user", ignore = true)
@Mapping(target = "cartItems", ignore = true)
CartDto toFlatDto(Cart cart);

@IterableMapping(qualifiedByName = "toFlatCartDto")
List<CartDto> toDtoList(List<Cart> cart);


}