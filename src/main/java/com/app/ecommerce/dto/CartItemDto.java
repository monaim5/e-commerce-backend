package com.app.ecommerce.dto;


import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.time.Instant;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDto {
	private Long id;
	private ProductDto product;
	private CartDto cart;
	private int quantity;
	private float price;
	private float discount;
	private Instant createdAt;
	private Instant UpdateAt;


}