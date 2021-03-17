package com.app.ecommerce.dto;


import com.app.ecommerce.models.CartStatus;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.util.List;
import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {
	private Long id;
	private UserDto user;
	private List<CartItemDto> cartItems;
	private String title;
	private String description;
	private Instant CreatedAt;
	private CartStatus status;


}