package com.example.eCommerce.service;

import com.example.eCommerce.dto.RequestDto.ItemRequestDto;
import com.example.eCommerce.exception.InvalidCustomerException;
import com.example.eCommerce.exception.InvalidProductException;
import com.example.eCommerce.model.Item;

public interface ItemService {
    public Item addItem(ItemRequestDto itemRequestDto) throws Exception;
}
