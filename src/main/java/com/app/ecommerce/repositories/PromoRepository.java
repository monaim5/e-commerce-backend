package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Promo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoRepository extends JpaRepository<Promo, Long> {
}
