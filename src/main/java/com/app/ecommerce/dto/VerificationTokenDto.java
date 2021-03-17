package com.app.ecommerce.dto;


import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VerificationTokenDto {
	private Long id;
	private String token;
	private UserDto user;


}