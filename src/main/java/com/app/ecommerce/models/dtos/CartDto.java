/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.dtos;

import com.app.ecommerce.models.enums.CartStatus;
import java.util.List;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {

	private Long id;
	private String title;
	private String description;
	private CartStatus status;
	private UserDto user;
	private List<CartItemDto> cartItems;

}