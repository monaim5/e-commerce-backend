/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.dtos;

import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class RefreshTokenRequest {

	private String refreshToken;
	private String username;

}