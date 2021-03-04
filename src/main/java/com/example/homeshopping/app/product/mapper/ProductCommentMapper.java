package com.example.homeshopping.app.product.mapper;

import com.example.homeshopping.app.product.domain.Product;
import com.example.homeshopping.app.product.domain.ProductCategory;
import com.example.homeshopping.app.product.domain.ProductComment;
import com.example.homeshopping.app.product.dto.ProductCommentDto;
import com.example.homeshopping.app.product.dto.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductCommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "productCommentDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "product", source = "product")
    ProductComment mapToProductComment(ProductCommentDto productCommentDto, Product product);

    @Mapping(target = "id", source = "productCommentDto.id")
    @Mapping(target = "createdDate", source = "productCommentDto.createdDate")
    @Mapping(target = "text", source = "productCommentDto.text")
    @Mapping(target = "product", source = "product")
    ProductComment mapToProductCommentUpdate(ProductCommentDto productCommentDto, Product product);
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "createdDate", ignore = true)
//    @Mapping(target = "text", source = "productCommentDto.text")
//    ProductComment mapToProductCommentUpdate(ProductCommentDto productCommentDto);

    @Mapping(target = "productId", expression = "java(productComment.getProduct().getProductId())")
    ProductCommentDto mapToProductCommentDto(ProductComment productComment);
}
