package com.example.product.Payload;

import lombok.Data;


import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long productId;

    private String name;

    private String productType;

    private String category;

    private BigDecimal basePrice;

    private double discount;

    private double finalPrice;


    private double gst;

    private double Delivery;

}


