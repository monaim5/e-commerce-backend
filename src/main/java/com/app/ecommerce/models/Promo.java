package com.app.ecommerce.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Promo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private float discountAmount;
    private boolean active;
    @ManyToOne
    private PromoType promoType;

    @OneToMany(mappedBy = "promo")
    private List<Product> products;
}