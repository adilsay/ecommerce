package com.example.eCommerce.service;

import com.example.eCommerce.Enum.ProductCategory;
import com.example.eCommerce.dto.RequestDto.SellerRequestDto;
import com.example.eCommerce.dto.ResponseDto.SellerResponseDto;
import com.example.eCommerce.exception.EmailAlradyPresentException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SellerService {
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) throws EmailAlradyPresentException;
    public List<SellerResponseDto> getSellerByProductCategory(ProductCategory category);

}
