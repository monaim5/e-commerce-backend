/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.entities;

import com.app.ecommerce.models.structure.AbstractEntity;
import com.app.ecommerce.models.enums.PaymentStatus;
import com.app.ecommerce.models.enums.PaymentMode;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment extends AbstractEntity {

	private String transactionId;
	private PaymentStatus status;
	private PaymentMode mode;
	private Instant paymentDate;
	private float amount;
	private String details;

	@ManyToOne
	private Order order;

}