package com.example.eCommerce.transformer;

import com.example.eCommerce.dto.RequestDto.ItemRequestDto;
import com.example.eCommerce.dto.ResponseDto.ItemResponseDto;
import com.example.eCommerce.model.Cart;
import com.example.eCommerce.model.Item;
import com.example.eCommerce.model.Ordered;

import java.util.ArrayList;
import java.util.List;

public class ItemTransformer {


    public static Item ItemRequestDtoToItem(ItemRequestDto itemRequestDto) {
        return Item.builder()
                .requiredQuentity(itemRequestDto.getRequiredQuantity())
                .build();
    }

    public static List<ItemResponseDto> CartToItemResponseDtoList(Cart cart){

        return setValues(cart.getItems());
    }

    public static List<ItemResponseDto> setValues(List<Item> itemList){
        List<ItemResponseDto> itemResponseDtoArrayList = new ArrayList<>();
        for(Item item:itemList) {
            ItemResponseDto itemResponseDto = ItemResponseDto.builder()
                    .priceOfOneItem(item.getProduct().getPrice())
                    .totalPrice(item.getRequiredQuentity() * item.getProduct().getPrice())
                    .productName(item.getProduct().getName())
                    .quantity(item.getRequiredQuentity())
                    .build();
            itemResponseDtoArrayList.add(itemResponseDto);
        }
        return itemResponseDtoArrayList;
    }

    public static void setOrderId(Ordered ordered){
        for (Item item: ordered.getItemList()){
            item.setOrder(ordered);
        }
    }
}
