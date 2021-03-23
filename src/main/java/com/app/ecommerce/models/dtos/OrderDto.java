/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.dtos;

import java.time.Instant;
import java.util.List;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

	private Long id;
	private Instant orderDate;
	private String code;
	private UserDto user;
	private List<OrderItemDto> orderItems;
	private List<PaymentDto> payments;

}