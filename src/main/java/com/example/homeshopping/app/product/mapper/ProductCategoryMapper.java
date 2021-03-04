package com.example.homeshopping.app.product.mapper;

import com.example.homeshopping.app.product.dto.ProductCategoryDto;
import com.example.homeshopping.app.product.dto.ProductCategoryRequest;
import com.example.homeshopping.app.product.domain.Product;
import com.example.homeshopping.app.product.domain.ProductCategory;
import com.example.homeshopping.app.product.repository.ProductCategoryRepository;
import com.example.homeshopping.app.product.repository.ProductRepository;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.List;

//@Mapper(componentModel = "spring")
//public interface ProductCategoryMapper {
//
//    @InheritInverseConfiguration
//    @Mapping(target = "products", ignore = true)
//    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
//    ProductCategory mapDtoToProductCategory(ProductCategoryDto productCategoryDto);
//
////    @Mapping(target = "productCount", expression = "java(mapProducts(productCategory.getProducts()))")
//    @Mapping(target = "productCount", expression = "java(mapProducts(productCategory))")
//    ProductCategoryDto mapProductCategoryToDto(ProductCategory productCategory);
//
////    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
////    @Mapping(target = "createdDate", ignore = true)
//    ProductCategory mapRequestToProductCategory(ProductCategoryRequest productCategoryRequest);
//
////    default Integer mapProducts(ProductCategory productCategory) {
////        return numberOfProducts.size();
////    }
//
//    default Integer mapProducts(ProductCategory productCategory) {
//        return productCategory.getProducts().size();
//    }
//
//}


@Mapper(componentModel = "spring")
public abstract class ProductCategoryMapper {

    @Autowired
    private ProductRepository productRepository;

    @InheritInverseConfiguration
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    public abstract ProductCategory mapDtoToProductCategory(ProductCategoryDto productCategoryDto);

    //    @Mapping(target = "productCount", expression = "java(mapProducts(productCategory.getProducts()))")
    @Mapping(target = "productCount", expression = "java(mapProducts(productCategory))")
    public abstract ProductCategoryDto mapProductCategoryToDto(ProductCategory productCategory);

    //    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
//    @Mapping(target = "createdDate", ignore = true)
    public abstract ProductCategory mapRequestToProductCategory(ProductCategoryRequest productCategoryRequest);

//    default Integer mapProducts(ProductCategory productCategory) {
//        return numberOfProducts.size();
//    }

    Integer mapProducts(ProductCategory productCategory) {
        List<Product> products = productRepository.findAllByProductCategory(productCategory);
        int res = products.size();
        return res;
    }

}
