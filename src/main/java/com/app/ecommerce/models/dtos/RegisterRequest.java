/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.dtos;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class RegisterRequest {

	private String email;
	private String password;

}