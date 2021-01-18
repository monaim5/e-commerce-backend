package com.app.ecommerce.services;

import com.app.ecommerce.dto.PhotoDto;
import com.app.ecommerce.dto.ProductDto;
import com.app.ecommerce.dto.PromoDto;
import com.app.ecommerce.dto.PromoTypeDto;
import com.app.ecommerce.exceptions.MonaimException;
import com.app.ecommerce.mappers.PromoMapper;
import com.app.ecommerce.models.*;
import com.app.ecommerce.repositories.PhotoRepository;
import com.app.ecommerce.repositories.ProductRepository;
import com.app.ecommerce.repositories.PromoRepository;
import com.app.ecommerce.repositories.PromoTypeRepository;
import com.app.ecommerce.specifications.PromoSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PromoService {
    private final PromoRepository promoRepository;
    private final PromoTypeRepository promoTypeRepository;
    private final ProductRepository productRepository;
    private final PhotoRepository photoRepository;
    private final PromoMapper promoMapper;

    public List<PromoDto> list(Optional<String> promoType) {
        Specification<Promo> promoTypeSpec = PromoSpecification.promoTypeEquals(promoType.orElse(null));

        Specification<Promo> specification = Specification.where(promoTypeSpec);

        return this.promoRepository.findAll(specification)
                .stream().map(promoMapper::mapToDto).collect(Collectors.toList());
    }

    public PromoDto create(PromoDto promoDto) {
        PromoType promoType = promoTypeRepository.findById(promoDto.getPromoType()).orElseThrow(() ->
                new MonaimException("please create this type before assign to it")
        );

        List<Product> promoProducts = this.productRepository.findAllByIdIn(
                promoDto.getProducts().stream().map(ProductDto::getId).collect(Collectors.toList())
        );

        List<Long> photoIds = promoDto.getBanners().stream().map(PhotoDto::getId).collect(Collectors.toList());
        List<Photo> photos = photoRepository.findAllById(photoIds);

        Promo promo = this.promoRepository.save(promoMapper.mapToPromo(promoDto, promoType));
        photos.forEach(photo -> {
            photo.setPromo(promo);
            this.photoRepository.save(photo);
        });

        promoProducts.forEach(product -> {
            product.setPromo(promo);
            this.productRepository.save(product);
        });

        promoDto.setId(promo.getId());
        return promoDto;
    }

    public PromoDto update(Long id, PromoDto promoDto) {
        assert id.equals(promoDto.getId());
        Promo promo = promoRepository.findById(id).orElseThrow(() ->
                new MonaimException("no such promo"));
        PromoType promoType = promoTypeRepository.findById(promoDto.getPromoType()).orElseThrow(() ->
                new MonaimException("please create this type before assign to it")
        );
        List<Product> promoProducts = this.productRepository.findAllByIdIn(
                promoDto.getProducts().stream().map(ProductDto::getId).collect(Collectors.toList())
        );
        updatePromoFromDto(promo, promoDto, promoType, promoProducts);
        this.promoRepository.save(promo);

        return promoDto;
    }

    private void updatePromoFromDto(Promo promo, PromoDto promoDto, PromoType promoType, List<Product> promoProducts) {
        promo.setPromoType(promoType);
        promo.setTitle(promoDto.getTitle());
        promo.setActive(promoDto.isActive());
        promo.setDiscountAmount(promoDto.getDiscountAmount());
        promo.setEndDate(promoDto.getEndDate());
        promo.setStartDate(promoDto.getStartDate());
        promo.setAndOverrideProducts(promoProducts);
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
}
