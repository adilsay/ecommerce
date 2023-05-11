package com.example.eCommerce.service.Impl;

import com.example.eCommerce.Enum.ProductCategory;
import com.example.eCommerce.dto.RequestDto.ProductRequestDto;
import com.example.eCommerce.dto.ResponseDto.ProductResponseDto;
import com.example.eCommerce.exception.InvalidSellerException;
import com.example.eCommerce.model.Product;
import com.example.eCommerce.model.Seller;
import com.example.eCommerce.repository.ProductRepository;
import com.example.eCommerce.repository.SellerRepository;
import com.example.eCommerce.service.ProductService;
import com.example.eCommerce.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws InvalidSellerException {
        Seller seller;
        try{
            seller = sellerRepository.findById((productRequestDto.getSellerId())).get();
        } catch (Exception e){
            throw new InvalidSellerException("Seller Does Not Exist");
        }

        Product product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);
        // add product to current product of seller
        seller.getProducts().add(product);
        sellerRepository.save(seller); // this save both saller and product

        // prepare response dto
        return ProductTransformer.ProductToProductResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getProductByCategory(ProductCategory category) {
        List<Product> products = productRepository.findByProductCategory(category);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        for(Product product: products) {
            productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        return productResponseDtos;
    }

    @Override
    public List<ProductResponseDto> getProductByPriceAndCategory(int price, ProductCategory productCategory) {
        List<Product> products = productRepository.findByPriceAndCategory(price, productCategory);

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
            for(Product product:products)
                productResponseDtos.add(ProductTransformer.ProductToProductResponseDto(product));

       return productResponseDtos;

    }
}
