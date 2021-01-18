package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Promo;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PromoRepository extends JpaRepository<Promo, Long> {
    List<Promo> findAll(Specification<Promo> specification);
}
