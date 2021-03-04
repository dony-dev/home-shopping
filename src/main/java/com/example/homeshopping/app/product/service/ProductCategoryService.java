package com.example.homeshopping.app.product.service;

import com.example.homeshopping.app.product.domain.Product;
import com.example.homeshopping.app.product.dto.ProductCategoryDto;
import com.example.homeshopping.app.product.dto.ProductCategoryRequest;
import com.example.homeshopping.app.product.domain.ProductCategory;
import com.example.homeshopping.app.product.dto.ProductResponse;
import com.example.homeshopping.app.product.mapper.ProductCategoryMapper;
import com.example.homeshopping.app.product.repository.ProductCategoryRepository;
import com.example.homeshopping.app.product.repository.ProductRepository;
import com.example.homeshopping.error.exception.HomeShoppingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;
    private final ProductCategoryMapper productCategoryMapper;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository, ProductRepository productRepository, ProductCategoryMapper productCategoryMapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.productRepository = productRepository;
        this.productCategoryMapper = productCategoryMapper;
    }

    // Create product-category
    @Transactional
    public void save(ProductCategoryDto productCategoryDto) {
        ProductCategory productCategory = productCategoryMapper.mapDtoToProductCategory(productCategoryDto);
        if (productCategoryRepository.findByCategoryName(productCategoryDto.getCategoryName()).isPresent()) {
            throw new HomeShoppingException("Category Already Exists");
        }
//        productCategoryRepository.save(productCategoryMapper.mapDtoToProductCategory(productCategoryDto));
        productCategoryRepository.save(productCategory);
    }

    // Get all product-category
    @Transactional
    public List<ProductCategoryDto> getAll() {
        return productCategoryRepository.findAll().stream()
                .map(productCategoryMapper::mapProductCategoryToDto).collect(Collectors.toList());
    }

    // Get product-category by id
    @Transactional
    public ProductCategoryDto getProductCategory(Long id) {
        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(() -> new HomeShoppingException(""));
        return productCategoryMapper.mapProductCategoryToDto(productCategory);
    }

    // Update product-category by id
    @Transactional
    public void updateProductCategory(ProductCategoryRequest productCategoryRequest) {
        ProductCategory productCategory = productCategoryRepository.findById(productCategoryRequest.getId()).orElseThrow(() -> new HomeShoppingException(""));
        if (productRepository.findAllByProductCategory(productCategory).size() > 1) {
            throw new HomeShoppingException("Cannot Update Category");
        }
        if (productCategoryRepository.findByCategoryName(productCategoryRequest.getCategoryName()).isPresent()) {
            throw new HomeShoppingException("Category Already Exists");
        }
        productCategoryRequest.setCreatedDate(productCategory.getCreatedDate());
        productCategoryRepository.save(productCategoryMapper.mapRequestToProductCategory(productCategoryRequest));
    }

    // Delete product-category by id
    @Transactional
    public void deleteProductCategory(Long id) {
        ProductCategory productCategory = productCategoryRepository.findById(id).orElseThrow(() -> new HomeShoppingException(""));
        productCategoryRepository.delete(productCategory);
    }

    public Page<ProductCategoryDto> getProductCategoryByPage(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<ProductCategory> productCategoryPage = productCategoryRepository.findAll(pageRequest);
        List<ProductCategoryDto> productResponseListPage = productCategoryPage.stream().map(productCategoryMapper::mapProductCategoryToDto).collect(Collectors.toList());
        return new PageImpl<>(productResponseListPage, pageRequest, productCategoryPage.getTotalElements());
    }
}
