package com.example.homeshopping.app.product.dto;

import java.util.Objects;

public class ProductCategoryDto {

    private Long id;
    private String categoryName;

    private String description;
    private Integer productCount;

    public ProductCategoryDto() {
    }

    public ProductCategoryDto(ProductCategoryDtoBuilder productCategoryDtoBuilder) {
        id = productCategoryDtoBuilder.id;
        categoryName = productCategoryDtoBuilder.categoryName;
        description = productCategoryDtoBuilder.description;
        productCount = productCategoryDtoBuilder.productCount;
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

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProductCategoryDto)) {
            return false;
        }
        ProductCategoryDto productCategoryDto = (ProductCategoryDto) o;
        return Objects.equals(id, productCategoryDto.id) && Objects.equals(categoryName, productCategoryDto.categoryName) && Objects.equals(description, productCategoryDto.description) && Objects.equals(productCount, productCategoryDto.productCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryName, description, productCount);
    }

    @Override
    public String toString() {
        return "ProductCategoryDto{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", productCount=" + productCount +
                '}';
    }

    public static class ProductCategoryDtoBuilder {
        private Long id;
        private String categoryName;

        private String description = "";
        private Integer productCount = 0;

        public ProductCategoryDtoBuilder(Long id, String categoryName) {
            this.id = id;
            this.categoryName = categoryName;
        }

        public ProductCategoryDtoBuilder description(String value) {
            description = value;
            return this;
        }

        public ProductCategoryDtoBuilder productCount(Integer value) {
            productCount = value;
            return this;
        }

        public ProductCategoryDto build() {
            return new ProductCategoryDto(this);
        }
    }
}
