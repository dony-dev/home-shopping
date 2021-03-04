package com.example.homeshopping.app.product.web;

import com.example.homeshopping.app.product.dto.ProductCommentDto;
import com.example.homeshopping.app.product.service.ProductCommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product/comments")
public class ProductCommentController {

    private final ProductCommentService productCommentService;

    public ProductCommentController(ProductCommentService productCommentService) {
        this.productCommentService = productCommentService;
    }

    // Create product comment
    @PostMapping
    public ResponseEntity<Void> createProductComment(@RequestBody ProductCommentDto productCommentDto) {
        productCommentService.createProductComment(productCommentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Get product-comments by product
    @GetMapping(value = "/by-product/{productId}")
    public ResponseEntity<List<ProductCommentDto>> getAllProductCommentsForProduct(@PathVariable("productId") Long productId) {
        List<ProductCommentDto> productCommentDtoList = productCommentService.getProductCommentByProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(productCommentDtoList);
    }

    // Update product-comment by id
    @PutMapping(value = "/{id}/edit")
    public ResponseEntity<Void> updateProductComment(@RequestBody @Valid ProductCommentDto productCommentDto, @PathVariable("id") Long id) {
        productCommentService.updateProductComment(productCommentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Delete product-comment by id
    @DeleteMapping(value = "/{id}/delete")
    public ResponseEntity<Void> deleteProductComment(@PathVariable("id") Long id) {
        productCommentService.deleteProductComment(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
