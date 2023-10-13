package com.example.demo.api.rest;

import com.example.demo.domain.dto.CreateProductRequest;
import com.example.demo.domain.dto.ProductDto;
import com.example.demo.domain.entity.Product;
import com.example.demo.domain.exceptions.ProductAlreadyExistException;
import com.example.demo.domain.exceptions.ProductNotFoundException;
import com.example.demo.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products/all")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/products/find/{name}")
    @PreAuthorize("hasAuthority('read')")
    public ResponseEntity<ProductDto> getProductByName(@PathVariable String name) throws ProductNotFoundException {
        return ResponseEntity.ok(productService.getProductByName(name));
    }

    @PostMapping("/products")
    @PreAuthorize("hasAuthority('modification')")
    public ResponseEntity<ProductDto> createProduct(@RequestBody CreateProductRequest product) throws ProductAlreadyExistException {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @PatchMapping("/products")
    @PreAuthorize("hasAuthority('modification')")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(product));
    }

    @DeleteMapping("/products")
    @PreAuthorize("hasAuthority('modification')")
    public ResponseEntity deleteProduct(@RequestBody String productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok()
                .body(format("product with id= %s deleted", productId));
    }
}
