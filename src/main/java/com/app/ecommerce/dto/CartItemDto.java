package com.app.ecommerce.dto;

import java.time.Instant;

public class CartItemDto {
    private Long productId;
    private Long cartId;

    private int quantity;
//    private float price;
    private float discount;
    private Instant createdAt;
    private Instant UpdateAt;
}
