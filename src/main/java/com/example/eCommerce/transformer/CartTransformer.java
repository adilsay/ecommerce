package com.example.eCommerce.transformer;

import com.example.eCommerce.dto.ResponseDto.CartResponseDto;
import com.example.eCommerce.model.Cart;
import com.example.eCommerce.model.Item;

import java.util.ArrayList;

public class CartTransformer {
    public static CartResponseDto CartToCartResponseDto(Cart cart){
        return CartResponseDto.builder()
                .totalCost(cart.getTotalCost())
                .customerName(cart.getCustomer().getName())
                .numberOfItems(cart.getNumberOfItems())
                .items(ItemTransformer.CartToItemResponseDtoList(cart))
                .build();
    }
    public static void emptyTheCart(Cart cart){
        for (Item item:cart.getItems()){
            item.setCart(null);
        }
        cart.setNumberOfItems(0);
        cart.setTotalCost(0);
        cart.setItems(new ArrayList<>());
    }
}
