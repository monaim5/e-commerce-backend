/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.entities;

import com.app.ecommerce.models.structure.AbstractEntity;
import com.app.ecommerce.models.enums.UserStatus;
import java.time.Instant;
import java.util.List;
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
public class User extends AbstractEntity {


	@OneToMany(mappedBy = "user")
	private List<Cart> carts;
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

	@OneToMany(mappedBy = "user")
	private List<Order> order;

}