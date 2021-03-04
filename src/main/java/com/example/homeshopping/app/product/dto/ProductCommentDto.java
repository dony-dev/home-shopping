package com.example.homeshopping.app.product.dto;

import java.time.Instant;

public class ProductCommentDto {

    private Long id;
    private String text;
    private Instant createdDate;

    private Long productId;

    public ProductCommentDto() {
    }

    public ProductCommentDto(Long id, String text, Instant createdDate, Long productId) {
        this.id = id;
        this.text = text;
        this.createdDate = createdDate;
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductCommentDto{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", createdDate=" + createdDate +
                ", productId=" + productId +
                '}';
    }
}
