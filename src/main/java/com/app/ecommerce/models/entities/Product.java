/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.entities;

import com.app.ecommerce.models.structure.AbstractEntity;
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
public class Product extends AbstractEntity {

	private String title;
	private String description;
	private String designation;
	private float rate;
	private float netPrice;
	private int sales;
	private boolean available;
	private int quantity;
	private float VATRate;
	private float grossPrice;

	@OneToMany(mappedBy = "product")
	private List<Photo> photos;

	@OneToMany(mappedBy = "product")
	private List<CartItem> cartItems;

	@OneToMany(mappedBy = "product")
	private List<OrderItem> orderItems;

	@ManyToOne
	private Category category;

	@ManyToOne
	private Promo promo;

}