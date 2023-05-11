package com.example.eCommerce.transformer;

import com.example.eCommerce.dto.RequestDto.SellerRequestDto;
import com.example.eCommerce.dto.ResponseDto.SellerResponseDto;
import com.example.eCommerce.model.Seller;
import org.springframework.stereotype.Repository;

public class SellerTransformer {

    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .age(sellerRequestDto.getAge())
                .emailId(sellerRequestDto.getEmailId())
                .mobNo(sellerRequestDto.getMobNo())
                .build();
    }

    public static SellerResponseDto SellerToSellerResponseDto(Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .age(seller.getAge())
                .build();
    }
}
