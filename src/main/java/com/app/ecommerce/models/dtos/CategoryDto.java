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
public class CategoryDto {

	private Long id;
	private String name;
	private String description;
	private String icon;
	private List<ProductDto> products;

}
