package com.app.ecommerce.models;

import com.app.ecommerce.exceptions.MonaimException;
import lombok.*;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;

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
    private boolean available;
    @OneToMany(mappedBy = "product")
    private Collection<Photo> photos;
    @ManyToOne
    private Category category;

}
