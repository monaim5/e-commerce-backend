/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.dtos;

import com.app.ecommerce.models.enums.PaymentStatus;
import com.app.ecommerce.models.enums.PaymentMode;
import java.time.Instant;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentDto {

	private Long id;
	private String transactionId;
	private PaymentStatus status;
	private PaymentMode mode;
	private Instant paymentDate;
	private float amount;
	private String details;
	private OrderDto order;

}