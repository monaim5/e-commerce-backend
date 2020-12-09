package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {
    @RestResource(path = "/available")
    public List<Product> findByAvailableIsTrue();
    @RestResource(path = "/productByKeyword")
    public List<Product> findByNameContains(@Param("q") String q);

//    @RestResource(path = "/productByKeyword")
//    @Query("select p from Product where p.name like :q")
//    public List<Product> search(@Param("q") String q);
}
