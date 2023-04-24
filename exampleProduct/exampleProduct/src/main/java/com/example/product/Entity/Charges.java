package com.example.product.Entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "charges")
public class Charges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "charge_id")
    private Long chargeId;

    @Column(name = "gst")
    private double gst;

    @Column(name = "delivery")
    private double delivery;

    @OneToOne(mappedBy = "charge")
    private Product product;

}
