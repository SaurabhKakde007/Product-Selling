package com.example.product.Repository;

import com.example.product.Entity.Charges;
import com.example.product.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargesRepository extends JpaRepository<Charges,Long> {
}

