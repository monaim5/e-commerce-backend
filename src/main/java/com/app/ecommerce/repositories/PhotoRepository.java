/**
 * This class has genearate by ecommerce-mda
 * Author: monaim
 */

package com.app.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.ecommerce.models.entities.Photo;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
//    void updateProduct(List<Long> photoIds, Long id);
}
