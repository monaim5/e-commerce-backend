package com.app.ecommerce.controllers;

import com.app.ecommerce.models.dtos.ResponsePayload;
import com.app.ecommerce.models.dtos.ProductDto;
import com.app.ecommerce.models.dtos.PromoDto;
import com.app.ecommerce.models.dtos.PromoTypeDto;
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
    public ResponseEntity<ResponsePayload<List<PromoDto>>> listPromos(@RequestParam("promoType") Optional<String> promoType) throws InterruptedException {
        ResponsePayload<List<PromoDto>> responsePayload = new ResponsePayload<>("Promos has been retrieved successfully", this.promoService.list(promoType));
        return ResponseEntity.status(HttpStatus.OK).body(responsePayload);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePayload<PromoDto>> retrievePromo(@PathVariable("id") Long id) throws InterruptedException {
        ResponsePayload<PromoDto> responsePayload = new ResponsePayload<>("Promos has been retrieved successfully", this.promoService.retrieve(id));
        return ResponseEntity.status(HttpStatus.OK).body(responsePayload);
    }

    @GetMapping(value = "/types")
    public ResponseEntity<ResponsePayload<List<PromoTypeDto>>> listPromoTypes() {
        ResponsePayload<List<PromoTypeDto>> responsePayload = new ResponsePayload<>("Promo types has been retrieved successfully", this.promoService.listPromoTypes());
        return ResponseEntity.status(HttpStatus.OK).body(responsePayload);
    }

    @GetMapping(value = "/{promoId}/products")
    public ResponseEntity<ResponsePayload<List<ProductDto>>> listPromoProducts(@PathVariable("promoId") Long id) {
        ResponsePayload<List<ProductDto>> responsePayload = new ResponsePayload<>("Products for the given promo has benn retrieved successfully", this.promoService.listPromoProducts(id));
        return ResponseEntity.status(HttpStatus.OK).body(responsePayload);
    }

    // ------------------------ PUT ------------------------

    @PutMapping("/{id}")
    public ResponseEntity<ResponsePayload<PromoDto>> updatePromo(@PathVariable("id") Long id, @RequestBody PromoDto promodto) {
        ResponsePayload<PromoDto> responsePayload = new ResponsePayload<>("Promo has been updated successfully", this.promoService.update(id, promodto));
        return ResponseEntity.status(HttpStatus.OK).body(responsePayload);
    }

    @PutMapping("/types/{id}")
    public ResponseEntity<ResponsePayload<PromoTypeDto>> updatePromoType(@PathVariable("id") Long id, @RequestBody PromoTypeDto promoTypeDto) {
        ResponsePayload<PromoTypeDto> responsePayload = new ResponsePayload<>("Promo Type has been updated successfully", this.promoService.updatePromoType(id, promoTypeDto));
        return ResponseEntity.status(HttpStatus.OK).body(responsePayload);
    }
//    @PutMapping(value = "/{promoId}/products")
//    public ResponseEntity<List<ProductDto>> updatePromoProducts(@PathVariable("promoId") Long id) {
//
//    }

    // ------------------------ POST ------------------------

    @PostMapping
    public ResponseEntity<ResponsePayload<PromoDto>> createPromo(@RequestBody PromoDto promoDto) {
        ResponsePayload<PromoDto> responsePayload = new ResponsePayload<>("Promo has been created successfully", this.promoService.create(promoDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(responsePayload);
    }

    @PostMapping(value = "/types")
    public ResponseEntity<ResponsePayload<PromoTypeDto>> createPromoType(@RequestBody PromoTypeDto promoTypeDto) {
        ResponsePayload<PromoTypeDto> responsePayload = new ResponsePayload<>("Promo Type has been created successfully", this.promoService.createPromoType(promoTypeDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(responsePayload);
    }

    // ------------------------ DELETE ------------------------

    @DeleteMapping
    public ResponseEntity<ResponsePayload<Long>> destroyPromo(@RequestParam("id") Long id) {
        this.promoService.destroy(id);
        ResponsePayload<Long> responsePayload = new ResponsePayload<>("Promo has deleted successfully", id);
        return ResponseEntity.status(HttpStatus.OK).body(responsePayload);
    }

    @DeleteMapping("/types")
    public ResponseEntity<ResponsePayload<Long>> destroyPromoType(@RequestParam("id") Long id) {
        this.promoService.destroyPromoType(id);
        ResponsePayload<Long> responsePayload = new ResponsePayload<>("Promo Type has been deleted successfully", id);
        return ResponseEntity.status(HttpStatus.OK).body(responsePayload);
    }
}
