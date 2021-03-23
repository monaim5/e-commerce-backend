/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.mappers;

import com.app.ecommerce.models.entities.User;
import com.app.ecommerce.models.dtos.UserDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.IterableMapping;
import java.util.List;


@Mapper(componentModel = "spring", uses = {CartMapper.class, OrderMapper.class, })
public interface UserMapper {

User toEntity(UserDto userDto);

UserDto toDto(User user);

@Named("toFlatUserDto")
@Mapping(target = "carts", ignore = true)
@Mapping(target = "order", ignore = true)
UserDto toFlatDto(User user);

@IterableMapping(qualifiedByName = "toFlatUserDto")
List<UserDto> toDtoList(List<User> user);


}