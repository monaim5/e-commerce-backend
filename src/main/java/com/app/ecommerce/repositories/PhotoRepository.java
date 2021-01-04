package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
