/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.dtos;

import java.util.List;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

	private Long id;
	private String title;
	private String description;
	private String designation;
	private float rate;
	private float netPrice;
	private int sales;
	private boolean available;
	private int quantity;
	private float VATRate;
	private float grossPrice;
	private List<PhotoDto> photos;
	private List<CartItemDto> cartItems;
	private List<OrderItemDto> orderItems;
	private CategoryDto category;
	private PromoDto promo;

}