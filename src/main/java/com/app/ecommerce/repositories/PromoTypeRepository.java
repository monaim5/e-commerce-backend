package com.app.ecommerce.repositories;

import com.app.ecommerce.models.PromoType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromoTypeRepository extends JpaRepository<PromoType, String> {
}
