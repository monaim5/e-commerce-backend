package com.app.ecommerce.mappers;

import com.app.ecommerce.dto.CartItemDto;
import com.app.ecommerce.models.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CartItemMapper {

    public CartItem toEntity(CartItemDto cartItemDto);
    public CartItemDto toDto(CartItem cartItem);
}
