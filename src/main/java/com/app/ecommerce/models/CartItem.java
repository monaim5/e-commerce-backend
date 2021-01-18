package com.app.ecommerce.models;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class CartItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Cart cart;

    private int quantity;
    private float price;
    private float discount;
    private Instant createdAt;
    private Instant UpdateAt;

}
