package com.app.ecommerce.services;

import com.app.ecommerce.dto.ProductDto;
import com.app.ecommerce.dto.PromoDto;
import com.app.ecommerce.dto.PromoTypeDto;
import com.app.ecommerce.exceptions.MonaimException;
import com.app.ecommerce.mappers.PromoMapper;
import com.app.ecommerce.models.Product;
import com.app.ecommerce.models.Promo;
import com.app.ecommerce.models.PromoType;
import com.app.ecommerce.repositories.ProductRepository;
import com.app.ecommerce.repositories.PromoRepository;
import com.app.ecommerce.repositories.PromoTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PromoService {
    private final PromoRepository promoRepository;
    private final PromoTypeRepository promoTypeRepository;
    private final ProductRepository productRepository;
    private final PromoMapper promoMapper;

    public List<PromoDto> list() {
        return this.promoRepository.findAll().stream().map(promoMapper::mapToDto).collect(Collectors.toList());
    }

    public PromoDto create(PromoDto promoDto) {
        PromoType promoType = promoTypeRepository.findById(promoDto.getPromoType()).orElseThrow(() ->
                new MonaimException("please create this type before assign to it")
        );

        Promo promo = this.promoRepository.save(promoMapper.mapToPromo(promoDto, promoType));

        List<Product> promoProducts = this.productRepository.findAllByIdIn(
                promoDto.getProducts().stream().map(ProductDto::getId).collect(Collectors.toList())
        );

        promoProducts.forEach(product -> {
            System.out.println("product 1");
            product.setPromo(promo);
            this.productRepository.save(product);
        });
        promoDto.setId(promo.getId());
        return promoDto;
    }

    public Long destroy(Long id) {
        Promo promo = this.promoRepository.findById(id).orElseThrow(() -> new MonaimException("no such promo in db"));
        promo.getProducts().forEach(product -> product.setPromo(null));
        this.promoRepository.delete(promo);
        return id;
    }

    public PromoType createPromoType(PromoType promoType){
        return this.promoTypeRepository.save(promoType);
    }

    public String destroyPromoType(String promoTypeName) {
        PromoType promoType = promoTypeRepository.findById(promoTypeName).orElseThrow(() -> new MonaimException("no such promotype with this id"));
        this.promoTypeRepository.delete(promoType);
        return promoTypeName;
    }

    public List<PromoTypeDto> listPromoTypes() {
        return this.promoTypeRepository.findAll().stream().map(this::mapToPromoTypeDto).collect(Collectors.toList());
    }

    private PromoTypeDto mapToPromoTypeDto(PromoType promoType) {
        return PromoTypeDto.builder()
                .name(promoType.getName())
                .description(promoType.getDescription())
                .build();
    }

//    private Promo mapToPromo(PromoDto promoDto, PromoType promoType) {
//        return Promo.builder()
//                .id(promoDto.getId())
//                .title(promoDto.getTitle())
//                .discountAmount(promoDto.getDiscountAmount())
//                .active(promoDto.isActive())
//                .endDate(promoDto.getEndDate())
//                .startDate(promoDto.getStartDate())
//                .type(promoType)
//                .build();
//    }
//
//    private PromoDto mapToDto(Promo promo) {
//        return PromoDto.builder()
//                .id(promo.getId())
//                .title(promo.getTitle())
//                .active(promo.isActive())
//                .discountAmount(promo.getDiscountAmount())
//                .startDate(promo.getStartDate())
//                .endDate(promo.getEndDate())
//                .type(promo.getType().getName())
//                .build();
//    }
}
