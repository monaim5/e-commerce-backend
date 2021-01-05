package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

//@CrossOrigin(origins = "**", allowedHeaders = "*")
@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByNameEquals(String categoryName);
}
