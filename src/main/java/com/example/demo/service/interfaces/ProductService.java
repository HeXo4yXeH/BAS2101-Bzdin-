package com.example.demo.service.interfaces;

import com.example.demo.domain.dto.CreateProductRequest;
import com.example.demo.domain.dto.ProductDto;
import com.example.demo.domain.entity.Product;
import com.example.demo.domain.exceptions.ProductAlreadyExistException;
import com.example.demo.domain.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    ProductDto getProductByName(String name) throws ProductNotFoundException;

    ProductDto createProduct(CreateProductRequest product) throws ProductAlreadyExistException;

    List<Product> getAllProducts();

    ProductDto updateProduct(Product product);

    void deleteProduct(String productId);
}
