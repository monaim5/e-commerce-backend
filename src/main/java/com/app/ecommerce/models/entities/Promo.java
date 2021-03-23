/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.models.entities;

import com.app.ecommerce.models.structure.AbstractEntity;
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
public class Promo extends AbstractEntity {

	private String title;
	private Instant startDate;
	private Instant endDate;
	private float discountAmount;
	private boolean active;

	@OneToMany(mappedBy = "promo")
	private List<Product> products;

	@ManyToOne
	private PromoType promoType;

	@OneToMany(mappedBy = "promo")
	private List<Banner> banners;

}