package com.example.product.Payload;

import com.example.product.Entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ChargeResponseDto {
    private Long chargeId;
    private double gst;
    private double delivery;

    private Product product;


}
