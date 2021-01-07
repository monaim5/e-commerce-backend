package com.app.ecommerce.specifications;

import com.app.ecommerce.models.Category;
import com.app.ecommerce.models.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class ProductSpecification {
    public static Specification<Product> nameLike(String name) {
        return ((root, criteriaQuery, criteriaBuilder) -> (name == null) ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.like(root.get("name"), "%" + name + "%")
        );
    }

    public static Specification<Product> categoryEquals(String category) {
        return ((root, criteriaQuery, criteriaBuilder) -> (category == null) ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.equal(root.get("category").get("name"), category)
        );
    }
}
