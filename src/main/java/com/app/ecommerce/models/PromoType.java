package com.app.ecommerce.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class PromoType {
    @Id
    private String name;
    private String description;
    @OneToMany(mappedBy = "type")
    private List<Promo> promos;

}
