package com.example.homeshopping.app.product.repository;

import com.example.homeshopping.app.product.domain.Product;
import com.example.homeshopping.app.product.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByProductCategory(ProductCategory productCategory);

    Optional<Product> findAllByProductName(String name);
}
