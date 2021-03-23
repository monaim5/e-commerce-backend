/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.dtos;

import java.time.Instant;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class AuthenticationResponse {

	private String authenticationToken;
	private String username;
	private String refreshToken;
	private Instant expiresAt;

}