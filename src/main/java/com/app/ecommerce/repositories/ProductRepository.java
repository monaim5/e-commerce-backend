package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Category;
import com.app.ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory(Category category);

//    @RestResource(path = "/productByKeyword")
//    @Query("select p from Product where p.name like :q")
//    public List<Product> search(@Param("q") String q);
}
