package com.app.ecommerce.controllers;

import com.app.ecommerce.dto.PromoDto;
import com.app.ecommerce.dto.PromoTypeDto;
import com.app.ecommerce.models.PromoType;
import com.app.ecommerce.services.PromoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promos")
@AllArgsConstructor
public class PromoController {

    private final PromoService promoService;

    @GetMapping
    public ResponseEntity<List<PromoDto>> listPromos() {
        return ResponseEntity.status(HttpStatus.OK).body(this.promoService.list());
    }

    @GetMapping(value = "/types")
    public ResponseEntity<List<PromoTypeDto>> listPromoTypes() {
        return ResponseEntity.status(HttpStatus.OK).body(this.promoService.listPromoTypes());
    }

    @PostMapping
    public ResponseEntity<PromoDto> createPromo(@RequestBody PromoDto promoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.promoService.create(promoDto));
    }

    @PostMapping(value = "/types")
    public ResponseEntity<PromoType> createPromoType(@RequestBody PromoType promoType) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.promoService.createPromoType(promoType));
    }

    @DeleteMapping
    public ResponseEntity<Long> destroyPromo(@RequestParam("id") Long id) {
        this.promoService.destroy(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @DeleteMapping("/types")
    public ResponseEntity<String> destroyPromoType(@RequestParam("id") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(this.promoService.destroyPromoType(name));
    }
}
