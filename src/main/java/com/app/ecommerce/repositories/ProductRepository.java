package com.app.ecommerce.repositories;

import com.app.ecommerce.models.Category;
import com.app.ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    List<Product> findAllByCategory(Category category);

    List<Product> findAllByIdIn(List<Long> productIds);

//    @Query("select p from Product where p.category")
//    List<Product> findBySpecification(
//            @Param("category") Category category,
//            @Param("name") String name,
//            @Param("orderBy") String String
//    );



//    @RestResource(path = "/productByKeyword")
//    @Query("select p from Product where p.name like :q")
//    public List<Product> search(@Param("q") String q);
}
