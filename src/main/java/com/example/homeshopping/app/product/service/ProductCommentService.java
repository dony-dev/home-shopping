package com.example.homeshopping.app.product.service;

import com.example.homeshopping.app.product.domain.Product;
import com.example.homeshopping.app.product.domain.ProductComment;
import com.example.homeshopping.app.product.dto.ProductCommentDto;
import com.example.homeshopping.app.product.mapper.ProductCommentMapper;
import com.example.homeshopping.app.product.repository.ProductCommentRepository;
import com.example.homeshopping.app.product.repository.ProductRepository;
import com.example.homeshopping.error.exception.HomeShoppingException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductCommentService {

    private final ProductCommentRepository productCommentRepository;
    private final ProductCommentMapper productCommentMapper;
    private final ProductRepository productRepository;

    public ProductCommentService(ProductCommentRepository productCommentRepository, ProductCommentMapper productCommentMapper, ProductRepository productRepository) {
        this.productCommentRepository = productCommentRepository;
        this.productCommentMapper = productCommentMapper;
        this.productRepository = productRepository;
    }

    // Create product comment
    @Transactional
    public void createProductComment(ProductCommentDto productCommentDto) {
        Product product = productRepository.findById(productCommentDto.getProductId()).orElseThrow(() -> new HomeShoppingException(""));
        ProductComment productComment = productCommentMapper.mapToProductComment(productCommentDto, product);
        productCommentRepository.save(productComment);
    }

    // Get product-comments by product
    @Transactional
    public List<ProductCommentDto> getProductCommentByProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new HomeShoppingException(""));
        List<ProductCommentDto> productCommentDtoList = productCommentRepository.findByProduct(product).stream().map(productCommentMapper::mapToProductCommentDto).collect(Collectors.toList());
        return productCommentDtoList;
    }

    // Update product-comment by id
    @Transactional
    public void updateProductComment(ProductCommentDto productCommentDto) {
        Product product = productRepository.findById(productCommentDto.getProductId()).orElseThrow(() -> new HomeShoppingException(""));
        ProductComment productComment = productCommentRepository.findById(productCommentDto.getId()).orElseThrow(() -> new HomeShoppingException(""));
        productCommentDto.setCreatedDate(productComment.getCreatedDate());
        productCommentRepository.save(productCommentMapper.mapToProductCommentUpdate(productCommentDto, product));
    }

    // Delete product-comment by id
    @Transactional
    public void deleteProductComment(Long id) {
        ProductComment productComment = productCommentRepository.findById(id).orElseThrow(() -> new HomeShoppingException(""));
        productCommentRepository.delete(productComment);
    }

}
