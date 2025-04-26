package com.example.warehouse_accounting.repository;

import com.example.warehouse_accounting.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(Long id);
    Product findProductByName(String name);

}
