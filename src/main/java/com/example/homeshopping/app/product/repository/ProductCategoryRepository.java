package com.example.homeshopping.app.product.repository;

import com.example.homeshopping.app.product.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    Optional<ProductCategory> findAllByCategoryName(String categoryName);

    Optional<ProductCategory> findByCategoryName(String categoryName);

}
