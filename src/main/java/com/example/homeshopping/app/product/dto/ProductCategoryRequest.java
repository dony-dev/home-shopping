package com.example.homeshopping.app.product.dto;

import java.time.Instant;

public class ProductCategoryRequest {

    private Long id;
    private String categoryName;
    private String description;
    private Instant createdDate;

    public ProductCategoryRequest() {
    }

    public ProductCategoryRequest(Long id, String categoryName, String description, Instant createdDate) {
        this.id = id;
        this.categoryName = categoryName;
        this.description = description;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "ProductCategoryRequest{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
