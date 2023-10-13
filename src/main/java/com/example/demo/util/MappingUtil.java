package com.example.demo.util;

import com.example.demo.domain.dto.CreateProductRequest;
import com.example.demo.domain.dto.ProductDto;
import com.example.demo.domain.entity.Product;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class MappingUtil {

    public static ProductDto mapToProductDto(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .count(product.getCount())
                .requestTime(LocalDateTime.now())
                .build();
    }

    public static Product mapToProductFromRequest(CreateProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .count(request.getCount())
                .price(request.getPrice())
                .build();
    }
}
