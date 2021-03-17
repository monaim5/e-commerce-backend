package com.app.ecommerce.dto;


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
public class UserDto {
	private Long id;
	private List<CartDto> carts;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private String password;
	private Instant registeredAt;
	private Instant lastLogin;
	private boolean admin;
	private boolean vendor;
	private boolean enabled;


}