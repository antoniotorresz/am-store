package com.unir.ms_operator.repository;

import com.unir.ms_operator.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
