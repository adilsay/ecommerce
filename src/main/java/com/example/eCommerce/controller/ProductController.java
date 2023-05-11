package com.example.eCommerce.controller;

import com.example.eCommerce.Enum.ProductCategory;
import com.example.eCommerce.dto.RequestDto.ProductRequestDto;
import com.example.eCommerce.dto.ResponseDto.ProductResponseDto;
import com.example.eCommerce.exception.InvalidSellerException;
import com.example.eCommerce.service.Impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;
    @PostMapping("/add")
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto)
            throws InvalidSellerException {
        return productService.addProduct(productRequestDto);

    }
    @GetMapping("/getProduct/{category}")
    public List<ProductResponseDto> getProductByCategory(@PathVariable("category") ProductCategory category) {
            return productService.getProductByCategory(category);
    }

    @GetMapping("/get/{price}/{category}")
    public List<ProductResponseDto> getProductByPriceAndCategory(
            @PathVariable("price") int price,@PathVariable("category") ProductCategory productCategory) {

        return productService.getProductByPriceAndCategory(price, productCategory);
    }

}
