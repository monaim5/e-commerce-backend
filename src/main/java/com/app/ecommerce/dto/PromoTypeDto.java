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
public class PromoTypeDto {
	private String name;
	private String description;
	private List<PromoDto> promos;


}