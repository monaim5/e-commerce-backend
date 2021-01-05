package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Photo;
import com.app.ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    @Modifying
    @Query("update Photo ph set ph.product.id=:productId where ph.id in :photoIds")
    public void updateProduct(@Param("photoIds") List<Long> photoIds, @Param("productId") Long ProductId);
}
