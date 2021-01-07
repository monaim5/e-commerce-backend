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
    private String name;
    private String description;
    private String designation;
//    private float rate;
    private double price;
    private int quantity;
    private int sales;
    private boolean available;
    @OneToMany(mappedBy = "product")
    private List<Photo> photos;
    @ManyToOne
    private Category category;

}
