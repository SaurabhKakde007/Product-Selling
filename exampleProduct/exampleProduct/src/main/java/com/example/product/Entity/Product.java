package com.example.product.Entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "name")
    private String name;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "category")
    private String category;

    @Column(name = "base_price")
    private BigDecimal basePrice;

    @Column(name = "discount")
    private double discount;

    @Column(name = "final_price")
    private double finalPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "charge_id", referencedColumnName = "charge_id")
    private Charges charge;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(BigDecimal basePrice) {
        this.basePrice = basePrice;
    }

    public double getDiscount() {
        if(getCategory().equalsIgnoreCase("Electronics")) {
            discount = discount * 0.15;
        }

        if(getCategory().equalsIgnoreCase("Home Appliances")) {
            discount = discount * 0.22;
        }

        if(getCategory().equalsIgnoreCase("Clothing")) {
            discount = discount * 0.40;
        }

        if(getCategory().equalsIgnoreCase("Furniture")) {
            discount = discount * 0.10;
        }


        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Charges getCharge() {
        return charge;
    }

    public void setCharge(Charges charge) {
        this.charge = charge;
    }
}
