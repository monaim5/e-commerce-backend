package com.app.ecommerce.services;

import com.app.ecommerce.exceptions.MonaimException;
import com.app.ecommerce.models.dtos.BannerDto;
import com.app.ecommerce.models.dtos.ProductDto;
import com.app.ecommerce.models.dtos.PromoDto;
import com.app.ecommerce.models.dtos.PromoTypeDto;
import com.app.ecommerce.models.entities.Photo;
import com.app.ecommerce.models.entities.Product;
import com.app.ecommerce.models.entities.Promo;
import com.app.ecommerce.models.entities.PromoType;
import com.app.ecommerce.models.mappers.ProductMapper;
import com.app.ecommerce.models.mappers.PromoMapper;
import com.app.ecommerce.repositories.PhotoRepository;
import com.app.ecommerce.repositories.ProductRepository;
import com.app.ecommerce.repositories.PromoRepository;
import com.app.ecommerce.repositories.PromoTypeRepository;
import com.app.ecommerce.specifications.PromoSpecification;
import lombok.AllArgsConstructor;
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
    private final ProductMapper productMapper;

    public List<PromoDto> list(Optional<String> promoType) {
        Specification<Promo> promoTypeSpec = PromoSpecification.promoTypeEquals(promoType.orElse(null));

        Specification<Promo> specification = Specification.where(promoTypeSpec);

        return this.promoRepository.findAll(specification)
                .stream().map(promoMapper::toDto).collect(Collectors.toList());
    }

    public PromoDto create(PromoDto promoDto) {
        PromoType promoType = promoTypeRepository.findById(promoDto.getPromoType().getId()).orElseThrow(() ->
                new MonaimException("please create this type before assign to it")
        );

        List<Product> promoProducts = this.productRepository.findAllByIdIn(
                promoDto.getProducts().stream().map(ProductDto::getId).collect(Collectors.toList())
        );

        List<Long> photoIds = promoDto.getBanners().stream().map(BannerDto::getId).collect(Collectors.toList());
        List<Photo> photos = photoRepository.findAllById(photoIds);
        // TODO: there is something to do here
//        Promo promo = this.promoRepository.save(promoMapper.toEntity(promoDto, promoType));
        Promo promo = this.promoRepository.save(promoMapper.toEntity(promoDto));
        promo.setPromoType(promoType);

//        photos.forEach(photo -> {
//            photo.setPromo(promo);
//            this.photoRepository.save(photo);
//        });

        promoProducts.forEach(product -> {
            product.setPromo(promo);
            this.productRepository.save(product);
        });

        promoDto.setId(promo.getId());
        return promoDto;
    }

    public PromoDto update(Long id, PromoDto promoDto) {
        assert id.equals(promoDto.getId());
        // 1 - retrieve promo
        Promo promo = promoRepository.findById(id).orElseThrow(() ->
                new MonaimException("no such promo"));
        // 2 - retrieve it's promoType
        PromoType promoType = promoTypeRepository.findById(promoDto.getPromoType().getId()).orElseThrow(() ->
                new MonaimException("please create this type before assign to it")
        );
        // 3 - list products belongs to that Promo
        List<Product> promoProducts = this.productRepository.findAllByIdIn(
                promoDto.getProducts().stream().map(ProductDto::getId).collect(Collectors.toList())
        );
        promo = promoMapper.toEntity(promoDto);
        promo.setPromoType(promoType);
        // TODO: there is something to do here
//        updatePromoFromDto(promo, promoDto, promoType, promoProducts);
        this.promoRepository.save(promo);

        return promoDto;
    }
    public List<ProductDto> listPromoProducts(Long id) {
        return productRepository.findAllByPromoId(id).stream()
                .map(productMapper::toDto).collect(Collectors.toList());
    }

    public void updatePromoProducts(Long promoId, List<ProductDto> productDtos) {
        promoRepository.saveProducts(promoId,
                productDtos.stream().map(productMapper::toEntity).collect(Collectors.toList()));
    }

//    private void updatePromoFromDto(Promo promo, PromoDto promoDto, PromoType promoType, List<Product> promoProducts) {
//        promo.setPromoType(promoType);
//        promo.setTitle(promoDto.getTitle());
//        promo.setActive(promoDto.isActive());
//        promo.setDiscountAmount(promoDto.getDiscountAmount());
//        promo.setEndDate(promoDto.getEndDate());
//        promo.setStartDate(promoDto.getStartDate());
//        promo.setAndOverrideProducts(promoProducts);
//    }

    public void destroy(Long id) {
        Promo promo = this.promoRepository.findById(id).orElseThrow(() -> new MonaimException("no such promo in db"));
        promo.getProducts().forEach(product -> product.setPromo(null));
        this.promoRepository.delete(promo);
    }

    public PromoType createPromoType(PromoType promoType){
        return this.promoTypeRepository.save(promoType);
    }

    public void destroyPromoType(Long id) {
        PromoType promoType = promoTypeRepository.findById(id).orElseThrow(() -> new MonaimException("no such promotype with this id"));
        this.promoTypeRepository.delete(promoType);
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
