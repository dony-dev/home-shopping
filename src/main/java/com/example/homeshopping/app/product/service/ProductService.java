package com.example.homeshopping.app.product.service;

import com.example.homeshopping.app.product.domain.Product;
import com.example.homeshopping.app.product.domain.ProductCategory;
import com.example.homeshopping.app.product.dto.ProductRequest;
import com.example.homeshopping.app.product.dto.ProductResponse;
import com.example.homeshopping.app.product.mapper.ProductMapper;
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
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productMapper = productMapper;
    }

    // Create product
    @Transactional
    public void save(ProductRequest productRequest) {
//        ProductCategory productCategory = productCategoryRepository.findAllByCategoryName(productRequest.getCategoryName()).orElseThrow(() -> new HomeShoppingException(""));
        ProductCategory productCategory = productCategoryRepository.findById(productRequest.getCategoryId()).orElseThrow(() -> new HomeShoppingException(""));
        if (productRepository.findAllByProductName(productRequest.getProductName()).isPresent()) {
            throw new HomeShoppingException("Product Already Exists");
        }
        productRepository.save(productMapper.mapToProduct(productRequest, productCategory));
    }

    // Get all products
    @Transactional
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::mapToProductResponse).collect(Collectors.toList());
    }

    // Get product by product-category id
    @Transactional(readOnly = true)
    public List<ProductResponse> getProductsByCategoryId(Long productCategoryId) {
        ProductCategory productCategory = productCategoryRepository.findById(productCategoryId).orElseThrow(() -> new HomeShoppingException(""));
        List<Product> products = productRepository.findAllByProductCategory(productCategory);
        return products.stream().map(productMapper::mapToProductResponse).collect(Collectors.toList());
    }

    // Get product by product-category name ??
    @Transactional
    public List<ProductResponse> getProductsByCategoryName(String productCategoryName) {
        ProductCategory productCategory = productCategoryRepository.findAllByCategoryName(productCategoryName).orElseThrow(() -> new HomeShoppingException(""));
        List<Product> products = productRepository.findAllByProductCategory(productCategory);
        return products.stream().map(productMapper::mapToProductResponse).collect(Collectors.toList());
    }

    // Update product by product id
    @Transactional
    public void update(ProductRequest productRequest) {
        ProductCategory productCategory = productCategoryRepository.findById(productRequest.getCategoryId()).orElseThrow(() -> new HomeShoppingException(""));
        if (productRepository.findAllByProductName(productRequest.getProductName()).isPresent()) {
            throw new HomeShoppingException("Product Already Exists");
        }
        Product product = productRepository.findById(productRequest.getProductId()).orElseThrow(() -> new HomeShoppingException(""));
        productRequest.setCreatedDate(product.getCreatedDate());
        productRepository.save(productMapper.mapToProductUpdate(productRequest, productCategory));
    }

    // Delete product by product id
    @Transactional
    public void delete(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new HomeShoppingException(""));
        productRepository.delete(product);
    }

    // Get products by page
    public Page<ProductResponse> findAllByPage(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findAll(pageRequest);
        List<ProductResponse> productResponseListPage = productPage.stream().map(productMapper::mapToProductResponse).collect(Collectors.toList());
        return new PageImpl<>(productResponseListPage, pageRequest, productPage.getTotalElements());
    }

    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new HomeShoppingException(""));
        ProductResponse productResponse = productMapper.mapToProductResponse(product);
        return productResponse;
    }
}
