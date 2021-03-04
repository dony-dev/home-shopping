package com.example.homeshopping.app.product.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class ProductRatingDto {

    private Long id;

    @Min(1)
    @Max(5)
    private BigDecimal ratingScore;

    public ProductRatingDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(BigDecimal ratingScore) {
        this.ratingScore = ratingScore;
    }

    @Override
    public String toString() {
        return "ProductRatingDto{" +
                "id=" + id +
                ", ratingScore=" + ratingScore +
                '}';
    }
}
