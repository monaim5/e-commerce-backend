package com.app.ecommerce.models;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
public class Cart {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany
    private List<CartItem> cartItems;

    private String title;
    private String description;
    private Instant CreatedAt;
    private CartStatus status;
}
