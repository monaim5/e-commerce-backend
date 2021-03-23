/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.mappers;

import com.app.ecommerce.models.entities.Order;
import com.app.ecommerce.models.dtos.OrderDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.IterableMapping;
import java.util.List;


@Mapper(componentModel = "spring", uses = {UserMapper.class, OrderItemMapper.class, PaymentMapper.class, })
public interface OrderMapper {

Order toEntity(OrderDto orderDto);

@Mapping(target = "user", qualifiedByName = "toFlatUserDto")
OrderDto toDto(Order order);

@Named("toFlatOrderDto")
@Mapping(target = "user", ignore = true)
@Mapping(target = "orderItems", ignore = true)
@Mapping(target = "payments", ignore = true)
OrderDto toFlatDto(Order order);

@IterableMapping(qualifiedByName = "toFlatOrderDto")
List<OrderDto> toDtoList(List<Order> order);


}