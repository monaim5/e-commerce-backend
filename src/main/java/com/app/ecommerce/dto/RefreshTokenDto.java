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
public class RefreshTokenDto {
	private Long id;
	private String token;
	private Instant createdAt;


}