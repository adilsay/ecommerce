package com.example.eCommerce.service.Impl;

import com.example.eCommerce.Enum.ProductCategory;
import com.example.eCommerce.dto.RequestDto.SellerRequestDto;
import com.example.eCommerce.dto.ResponseDto.ProductResponseDto;
import com.example.eCommerce.dto.ResponseDto.SellerResponseDto;
import com.example.eCommerce.exception.EmailAlradyPresentException;
import com.example.eCommerce.model.Product;
import com.example.eCommerce.model.Seller;
import com.example.eCommerce.repository.ProductRepository;
import com.example.eCommerce.repository.SellerRepository;
import com.example.eCommerce.service.SellerService;
import com.example.eCommerce.transformer.ProductTransformer;
import com.example.eCommerce.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlradyPresentException {
        if(sellerRepository.findByEmailId(sellerRequestDto.getEmailId()) != null)
            throw new EmailAlradyPresentException("Email Alrady Present");

        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);
        Seller savedSeller = sellerRepository.save(seller);

//      prepare response dto
        SellerResponseDto sellerResponseDto = SellerTransformer.SellerToSellerResponseDto(savedSeller);
        return sellerResponseDto;

    }

    @Override
    public List<SellerResponseDto> getSellerByProductCategory(ProductCategory category) {
        List<Product> products = productRepository.findByProductCategory(category);

        List<SellerResponseDto> sellerResponseDtos = new ArrayList<>();

        for(Product product: products) {
            sellerResponseDtos.add(SellerTransformer.SellerToSellerResponseDto(product.getSeller()));
        }
        return sellerResponseDtos;
    }

}
