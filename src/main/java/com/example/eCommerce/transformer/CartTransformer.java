package com.example.eCommerce.transformer;

import com.example.eCommerce.dto.ResponseDto.CartResponseDto;
import com.example.eCommerce.model.Cart;

public class CartTransformer {
    public static CartResponseDto CartToCartResponseDto(Cart cart){
        return CartResponseDto.builder()
                .totalCost(cart.getTotalCost())
                .customerName(cart.getCustomer().getName())
                .numberOfItems(cart.getNumberOfItems())
                .items(ItemTransformer.CartToItemResponseDtoList(cart))
                .build();
    }
}
