package com.app.ecommerce.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Promo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date startDate;
    private Date endDate;
    private float discountAmount;
    private boolean active;
    @ManyToOne
    private PromoType type;
    @OneToMany(mappedBy = "promo")
    private List<Product> products;
}