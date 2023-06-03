package com.example.eCommerce.transformer;


import com.example.eCommerce.dto.ResponseDto.OrderResponseDto;
import com.example.eCommerce.model.Cart;
import com.example.eCommerce.model.Customer;
import com.example.eCommerce.model.Item;
import com.example.eCommerce.model.Ordered;

import java.util.ArrayList;
import java.util.List;
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
                .cardUsed(CardTransformer.maskCardNumber(ordered.getCardUsed()))
                .customerName(ordered.getCustomer().getName())
                .items(ItemTransformer.setValues(ordered.getItemList()))
                .totalValue(ordered.getTotalValue())
                .build();
    }


    public static Ordered itemToOrderTransformer(Item item, Customer customer) {
        List<Item> items = new ArrayList<>();
        items.add(item);
        return Ordered.builder()
                .orderNo(String.valueOf(UUID.randomUUID()))
                .customer(customer)
                .totalValue(item.getProduct().getPrice()*item.getRequiredQuentity())
                .itemList(items)
                .build();
//        ordered.getItemList().add(item);

    }
}
