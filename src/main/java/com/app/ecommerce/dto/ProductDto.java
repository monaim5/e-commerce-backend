package com.app.ecommerce.dto;


import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
	private Long id;
	private List<PhotoDto> photos;
	private CategoryDto category;
	private PromoDto promo;
	private List<CartItemDto> cartItems;
	private String title;
	private String description;
	private String designation;
	private float rate;
	private float price;
	private int quantity;
	private int sales;
	private boolean available;


}