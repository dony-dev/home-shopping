package com.example.homeshopping.app.product.dto;

import com.example.homeshopping.app.product.domain.ProductRating;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class ProductResponse {

    private Long productId;
    private String categoryName;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;

    private Integer productCommentCount;

    public ProductResponse() {
    }

    public ProductResponse(Long productId, String categoryName, String productName, String productDescription, BigDecimal productPrice, Integer productCommentCount) {
        this.productId = productId;
        this.categoryName = categoryName;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productCommentCount = productCommentCount;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductCommentCount() {
        return productCommentCount;
    }

    public void setProductCommentCount(Integer productCommentCount) {
        this.productCommentCount = productCommentCount;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "productId=" + productId +
                ", categoryName='" + categoryName + '\'' +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", productPrice=" + productPrice +
                ", productCommentCount=" + productCommentCount +
                '}';
    }
}
