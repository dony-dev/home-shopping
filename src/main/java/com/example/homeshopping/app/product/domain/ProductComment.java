package com.example.homeshopping.app.product.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Table(name = "tb_product_comment")
@Entity
public class ProductComment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Instant createdDate;

    @Lob
    private String text;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    private Product product;

    public ProductComment() {
    }

    public ProductComment(Long id, Instant createdDate, String text, Product product) {
        this.id = id;
        this.createdDate = createdDate;
        this.text = text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductComment{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", text='" + text + '\'' +
                ", product=" + product +
                '}';
    }
}
