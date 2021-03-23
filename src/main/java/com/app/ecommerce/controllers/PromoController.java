package com.app.ecommerce.controllers;

import com.app.ecommerce.models.dtos.ProductDto;
import com.app.ecommerce.models.dtos.PromoDto;
import com.app.ecommerce.models.dtos.PromoTypeDto;
import com.app.ecommerce.models.entities.PromoType;
import com.app.ecommerce.services.PromoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/promos")
@AllArgsConstructor
public class PromoController {

    private final PromoService promoService;

    // ------------------------ GET ------------------------
    @GetMapping
    public ResponseEntity<List<PromoDto>> listPromos(
            @RequestParam("promoType") Optional<String> promoType
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.promoService.list(promoType));
    }

    @GetMapping(value = "/types")
    public ResponseEntity<List<PromoTypeDto>> listPromoTypes() {
        return ResponseEntity.status(HttpStatus.OK).body(this.promoService.listPromoTypes());
    }

    @GetMapping(value = "/{promoId}/products")
    public ResponseEntity<List<ProductDto>> listPromoProducts(@PathVariable("promoId") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(promoService.listPromoProducts(id));
    }

    // ------------------------ PUT ------------------------

    @PutMapping("/{id}")
    public ResponseEntity<PromoDto> updatePromo(@PathVariable("id") Long id, @RequestBody PromoDto promodto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.promoService.update(id, promodto));
    }

//    @PutMapping(value = "/{promoId}/products")
//    public ResponseEntity<List<ProductDto>> updatePromoProducts(@PathVariable("promoId") Long id) {
//
//    }

    // ------------------------ POST ------------------------

    @PostMapping
    public ResponseEntity<PromoDto> createPromo(@RequestBody PromoDto promoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.promoService.create(promoDto));
    }

    @PostMapping(value = "/types")
    public ResponseEntity<PromoType> createPromoType(@RequestBody PromoType promoType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.promoService.createPromoType(promoType));
    }

    // ------------------------ DELETE ------------------------

    @DeleteMapping
    public ResponseEntity<Long> destroyPromo(@RequestParam("id") Long id) {
        this.promoService.destroy(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @DeleteMapping("/types")
    public ResponseEntity<String> destroyPromoType(@RequestParam("id") Long promoTypeId) {
        this.promoService.destroyPromoType(promoTypeId);
        return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }
}
