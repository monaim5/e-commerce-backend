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
public class PromoDto {

	private Long id;
	private String title;
	private Instant startDate;
	private Instant endDate;
	private float discountAmount;
	private boolean active;
	private List<ProductDto> products;
	private PromoTypeDto promoType;
	private List<BannerDto> banners;

}