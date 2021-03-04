package com.example.homeshopping.app.product.domain;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Table(name = "tb_product_category")
@Entity
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Instant createdDate;

    private String categoryName;

    @Lob
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Product> products;

    public ProductCategory() {
    }

    public ProductCategory(Long id, Instant createdDate, String categoryName, String description, List<Product> products) {
        this.id = id;
        this.createdDate = createdDate;
        this.categoryName = categoryName;
        this.description = description;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", products=" + products +
                '}';
    }
}
