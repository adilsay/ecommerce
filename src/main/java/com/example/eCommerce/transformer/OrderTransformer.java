package com.example.eCommerce.transformer;


import com.example.eCommerce.dto.ResponseDto.OrderResponseDto;
import com.example.eCommerce.model.Cart;
import com.example.eCommerce.model.Ordered;

import java.util.UUID;

public class OrderTransformer {

    public static Ordered cartToOrderTransformer(Cart cart){
        return Ordered.builder()
                .orderNo(String.valueOf(UUID.randomUUID()))
                .customer(cart.getCustomer())
                .itemList(cart.getItems())
                .totalValue(cart.getTotalCost())
                .build();
    }

    public static OrderResponseDto OrderToOrderResponseDTO(Ordered ordered){
        return OrderResponseDto.builder()
                .orderId(ordered.getOrderNo())
                .orderDate(ordered.getOrderDate())
                .cardUsed(ordered.getCardUsed())
                .customerName(ordered.getCustomer().getName())
                .items(ItemTransformer.setValues(ordered.getItemList()))
                .totalValue(ordered.getTotalValue())
                .build();
    }
}
