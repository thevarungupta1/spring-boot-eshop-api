package com.thevarungupta.eshop.rest.api.controller;

import com.thevarungupta.eshop.rest.api.entity.Product;
import com.thevarungupta.eshop.rest.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/products")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }


    @GetMapping("/cat/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok(productService.getProductsByCategoryId(categoryId));
    }

    @PostMapping("/cat/{categoryId}")
    public ResponseEntity<Product> saveProduct(@PathVariable("categoryId") Long categoryId, @RequestBody Product product) {
        return ResponseEntity.ok(productService.saveProduct(categoryId, product));
    }
}
