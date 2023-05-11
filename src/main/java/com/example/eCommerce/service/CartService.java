package com.example.eCommerce.service;

import com.example.eCommerce.dto.ResponseDto.CartResponseDto;
import com.example.eCommerce.exception.InvalidCartException;
import com.example.eCommerce.model.Item;

public interface CartService {
    public CartResponseDto saveCart(Item item, Integer customerId);

    void isValidCartToPlaceOrder(int cartId) throws InvalidCartException;
}
