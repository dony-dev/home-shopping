package com.example.homeshopping.app.product.mapper;

import com.example.homeshopping.app.product.domain.Product;
import com.example.homeshopping.app.product.domain.ProductCategory;
import com.example.homeshopping.app.product.dto.ProductRequest;
import com.example.homeshopping.app.product.dto.ProductResponse;
import com.example.homeshopping.app.product.repository.ProductCommentRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Autowired
    private ProductCommentRepository productCommentRepository;

    @Mapping(target = "productId", source = "productId")
    @Mapping(target = "categoryName", source = "productCategory.categoryName")
    @Mapping(target = "productCommentCount", expression = "java(productCommentCount(product))")
    public abstract ProductResponse mapToProductResponse(Product product);

    Integer productCommentCount(Product product) {
        return productCommentRepository.findByProduct(product).size();
    }

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "productCategory", source = "productCategory")
    @Mapping(target = "productName", source = "productRequest.productName")
    @Mapping(target = "productDescription", source = "productRequest.productDescription")
    @Mapping(target = "productPrice", source = "productRequest.productPrice")
    public abstract Product mapToProduct(ProductRequest productRequest, ProductCategory productCategory);

    @Mapping(target = "productId", expression = "java(productRequest.getProductId())")
    @Mapping(target = "createdDate", source = "productRequest.createdDate")
    @Mapping(target = "productCategory", source = "productCategory")
    @Mapping(target = "productName", source = "productRequest.productName")
    @Mapping(target = "productDescription", source = "productRequest.productDescription")
    @Mapping(target = "productPrice", source = "productRequest.productPrice")
    public abstract Product mapToProductUpdate(ProductRequest productRequest, ProductCategory productCategory);

}
