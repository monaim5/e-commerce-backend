/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.mappers;

import com.app.ecommerce.models.entities.OrderItem;
import com.app.ecommerce.models.dtos.OrderItemDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.IterableMapping;
import java.util.List;


@Mapper(componentModel = "spring", uses = {ProductMapper.class, OrderMapper.class, })
public interface OrderItemMapper {

OrderItem toEntity(OrderItemDto orderItemDto);

@Mapping(target = "product", qualifiedByName = "toFlatProductDto")
@Mapping(target = "order", qualifiedByName = "toFlatOrderDto")
OrderItemDto toDto(OrderItem orderItem);

@Named("toFlatOrderItemDto")
@Mapping(target = "product", ignore = true)
@Mapping(target = "order", ignore = true)
OrderItemDto toFlatDto(OrderItem orderItem);

@IterableMapping(qualifiedByName = "toFlatOrderItemDto")
List<OrderItemDto> toDtoList(List<OrderItem> orderItem);


}