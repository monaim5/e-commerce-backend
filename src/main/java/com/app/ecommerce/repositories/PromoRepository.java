/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.repositories;

import com.app.ecommerce.models.entities.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.app.ecommerce.models.entities.Promo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PromoRepository extends JpaRepository<Promo, Long> {
    List<Promo> findAll(Specification<Promo> specification);

    @Query("update Product p set p.promo.id = :id where p.id in :products")
    void saveProducts(Long id, List<Product> products);

}
