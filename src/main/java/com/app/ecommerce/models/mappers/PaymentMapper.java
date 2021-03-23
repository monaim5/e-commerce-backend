/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.mappers;

import com.app.ecommerce.models.entities.Payment;
import com.app.ecommerce.models.dtos.PaymentDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.IterableMapping;
import java.util.List;


@Mapper(componentModel = "spring", uses = {OrderMapper.class, })
public interface PaymentMapper {

Payment toEntity(PaymentDto paymentDto);

@Mapping(target = "order", qualifiedByName = "toFlatOrderDto")
PaymentDto toDto(Payment payment);

@Named("toFlatPaymentDto")
@Mapping(target = "order", ignore = true)
PaymentDto toFlatDto(Payment payment);

@IterableMapping(qualifiedByName = "toFlatPaymentDto")
List<PaymentDto> toDtoList(List<Payment> payment);


}