/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.repositories;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import com.app.ecommerce.models.entities.Product;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Modifying
    @Query("update Photo photo set photo.product.id = :id where photo.id in :photoIds")
    void setPhotos(@Param("id") Long id, @Param("photoIds") List<Long> photoIds);

//    List<Product> findAll(Specification<Product> specification, PageRequest id);

    List<Product> findAllByPromoId(Long id);

    List<Product> findAllByIdIn(List<Long> collect);

//    List<Product> findAllByPromo(Promo promo);

//    @Query("update Product p set p.promo.id = product.id where in :products")
//    void saveProducts(Long List<Product> products);
}
