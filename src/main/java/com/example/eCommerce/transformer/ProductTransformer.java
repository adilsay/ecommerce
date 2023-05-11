package com.example.eCommerce.transformer;

import com.example.eCommerce.Enum.ProductStatus;
import com.example.eCommerce.dto.RequestDto.ProductRequestDto;
import com.example.eCommerce.dto.ResponseDto.ProductResponseDto;
import com.example.eCommerce.model.Product;

public class ProductTransformer {
    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .name(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .productCategory(productRequestDto.getProductCategory())
                .quantity(productRequestDto.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public  static ProductResponseDto ProductToProductResponseDto(Product product){
            return ProductResponseDto.builder()
                    .productName(product.getName())
                    .productStatus(product.getProductStatus())
                    .quantity(product.getQuantity())
                    .sallerName(product.getSeller().getName())
                    .build();
    }
}
