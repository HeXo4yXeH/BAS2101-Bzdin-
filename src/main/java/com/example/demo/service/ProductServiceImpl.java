package com.example.demo.service;

import com.example.demo.domain.dto.CreateProductRequest;
import com.example.demo.domain.dto.ProductDto;
import com.example.demo.domain.entity.Product;
import com.example.demo.domain.exceptions.ProductAlreadyExistException;
import com.example.demo.domain.exceptions.ProductNotFoundException;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.interfaces.ProductService;
import com.example.demo.util.MappingUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.demo.util.MappingUtil.mapToProductDto;
import static com.example.demo.util.MappingUtil.mapToProductFromRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDto getProductByName(String name) throws ProductNotFoundException {
        return productRepository.findByName(name).stream()
                .map(MappingUtil::mapToProductDto)
                .findFirst()
                .orElseThrow(() -> new ProductNotFoundException(name));
    }

    @Override
    @Transactional
    public ProductDto createProduct(CreateProductRequest request) throws ProductAlreadyExistException {
        if (!productRepository.existsByName(request.getName())) {
            return mapToProductDto(productRepository.save(mapToProductFromRequest(request)));
        }
        throw new ProductAlreadyExistException(request.getName());
    }



    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAll();
    }

    @Override
    @Transactional
    public ProductDto updateProduct(Product product) {
        return mapToProductDto(productRepository.save(product));
    }

    @Override
    public void deleteProduct(String productId) {
        productRepository.deleteById(UUID.fromString(productId));
    }
}
