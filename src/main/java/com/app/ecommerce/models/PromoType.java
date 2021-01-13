package com.app.ecommerce.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class PromoType {
    @Id
    private String name;
    private String description;
    @OneToMany(mappedBy = "promoType")
    private List<Promo> promos;

}
