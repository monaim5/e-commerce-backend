/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.dtos;

import com.app.ecommerce.models.enums.UserStatus;
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
public class UserDto {

	private Long id;
	private List<CartDto> carts;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private String password;
	private Instant lastLogin;
	private boolean admin;
	private boolean vendor;
	private boolean customer;
	private UserStatus status;
	private List<OrderDto> order;

}