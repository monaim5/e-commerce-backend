package com.app.ecommerce.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Product implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "product")
    private List<Photo> photos;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Promo promo;

    @OneToMany
    private List<CartItem> cartItems;

    private String title;
    private String description;
    private String designation;
    private float rate;
    private float price;
    private int quantity;
    private int sales;
    private boolean available;

}
