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
public class CategoryDto {
	private Long id;
	private String name;
	private String description;
	private String icon;
	private String photo;
	private List<ProductDto> products;


}