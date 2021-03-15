package com.app.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Cart> carts;

    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String password;
    private Instant registeredAt;
    private Instant lastLogin;
    private boolean admin;
    private boolean vendor;
    private boolean enabled;
}
