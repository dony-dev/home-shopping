package com.example.homeshopping.app.product.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;

@Table(name = "tb_product_rating")
@Entity
public class ProductRating {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Instant createdDate;

    @Min(1)
    @Max(5)
    private BigDecimal ratingScore;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    private Product product;

    public ProductRating() {
    }

    public ProductRating(Long id, Instant createdDate, @Min(1) @Max(5) BigDecimal ratingScore, Product product) {
        this.id = id;
        this.createdDate = createdDate;
        this.ratingScore = ratingScore;
        this.product = product;
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

    public BigDecimal getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(BigDecimal ratingScore) {
        this.ratingScore = ratingScore;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductRating{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", ratingScore=" + ratingScore +
                ", product=" + product +
                '}';
    }
}
