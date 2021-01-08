package com.app.ecommerce.dto;

import com.app.ecommerce.models.PromoType;

import java.sql.Date;

public class PromoDto {
    private Long id;
    private Date startDate;
    private Date endDate;
    private float discountAmount;
    private boolean active;
    private PromoType type;
}
