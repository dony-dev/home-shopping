package com.example.homeshopping.app.product.web;

import com.example.homeshopping.app.product.dto.ProductRequest;
import com.example.homeshopping.app.product.dto.ProductResponse;
import com.example.homeshopping.app.product.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Create product
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest) {
        productService.save(productRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> productResponseList = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(productResponseList);
    }

    // Get product by product-category id
    @GetMapping(value = "/by-category-id/{id}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategoryId(Long id) {
        List<ProductResponse> productResponseList = productService.getProductsByCategoryId(id);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseList);
    }

    // Get product by product-id
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
        ProductResponse productResponse = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }

    // Get product by product-category name
    @GetMapping(value = "/by-category-name/{name}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategoryName(String name) {
        List<ProductResponse> productResponseList = productService.getProductsByCategoryName(name);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseList);
    }

    // Update product by product id
    @PutMapping(value = "/{id}/edit")
    public ResponseEntity<?> updateProduct(@RequestBody @Valid ProductRequest productRequest, @PathVariable Long id) {
        productService.update(productRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Delete product by product id
    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Get products by page
    @GetMapping(value = "/page")
    public Page<ProductResponse> getProductByPagination(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                        @RequestParam(name = "size", defaultValue = "10") Integer size) {
//        Page<ProductResponse> productResponsePage = productService.findAllByPage(page, size);
//        return (Page<ProductResponse>) ResponseEntity.status(HttpStatus.OK).body(productResponsePage);
        return productService.findAllByPage(page, size);
    }

}
