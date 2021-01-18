package com.app.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Photo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String path;
    private String url;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Product product;
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Promo promo;
}
