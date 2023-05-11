package com.example.eCommerce.service;

import com.example.eCommerce.Enum.ProductCategory;
import com.example.eCommerce.dto.RequestDto.ProductRequestDto;
import com.example.eCommerce.dto.ResponseDto.ProductResponseDto;
import com.example.eCommerce.exception.InvalidSellerException;

import java.util.List;

public interface ProductService {
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException;

    public List<ProductResponseDto> getProductByCategory(ProductCategory category);


    List<ProductResponseDto> getProductByPriceAndCategory(int price, ProductCategory productCategory);
}
