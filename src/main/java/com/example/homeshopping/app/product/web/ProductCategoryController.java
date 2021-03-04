package com.example.homeshopping.app.product.web;

import com.example.homeshopping.app.product.dto.ProductCategoryDto;
import com.example.homeshopping.app.product.dto.ProductCategoryRequest;
import com.example.homeshopping.app.product.service.ProductCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/category")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    // Create product-category
    @PostMapping
    public ResponseEntity<?> createProductCategory(@RequestBody @Valid ProductCategoryDto productCategoryDto) {
        productCategoryService.save(productCategoryDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Get all product-category
//    @GetMapping
//    public List<ProductCategoryDto> getAllProductCategory() {
//
//        return productCategoryService.getAll();
//    }
    @GetMapping
    public ResponseEntity<?> getAllProductCategory() {
        List<ProductCategoryDto> productCategoryDtos = productCategoryService.getAll();
        System.out.println(productCategoryDtos.toString());
//        return productCategoryService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(productCategoryDtos);
    }


    // Get product-category by id
    @GetMapping(value = "/{id}")
    public ProductCategoryDto getProductCategory(@PathVariable Long id) {
        return productCategoryService.getProductCategory(id);
    }

    // Update product-category by id
    @PutMapping(value = "/{id}/edit")
    public ResponseEntity<?> updateProductCategory(@RequestBody @Valid ProductCategoryRequest productCategoryRequest, @PathVariable Long id) {
        productCategoryService.updateProductCategory(productCategoryRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Delete product-category by id
    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<?> deleteProductCategory(@PathVariable Long id) {
        productCategoryService.deleteProductCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<?> getCategoryByPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                               @RequestParam(name = "size", defaultValue = "10") Integer size) {
        Page<ProductCategoryDto> productCategoryByPage = productCategoryService.getProductCategoryByPage(page, size);
//        List<ProductCategoryDto> productCategoryDtos = productCategoryByPage.getContent();
        return ResponseEntity.status(HttpStatus.OK).body(productCategoryByPage);
    }
}
