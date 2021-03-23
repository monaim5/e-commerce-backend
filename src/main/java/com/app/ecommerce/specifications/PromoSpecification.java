package com.app.ecommerce.specifications;

import com.app.ecommerce.models.entities.Product;
import com.app.ecommerce.models.entities.Promo;
import org.springframework.data.jpa.domain.Specification;

public class PromoSpecification {

    public static Specification<Promo> promoTypeEquals(String promoType) {
        return ((root, criteriaQuery, criteriaBuilder) -> (promoType == null) ?
                criteriaBuilder.conjunction() :
                criteriaBuilder.equal(root.get("promoType").get("name"), promoType)
        );
    }
}
