package com.example.eCommerce.service;

import com.example.eCommerce.dto.RequestDto.OrderRequestDto;
import com.example.eCommerce.dto.ResponseDto.OrderResponseDto;
import com.example.eCommerce.exception.InvalidCardException;
import com.example.eCommerce.exception.InvalidCartException;
import com.example.eCommerce.exception.InvalidQuentityException;
import com.example.eCommerce.model.Item;

public interface OrderService {
     OrderResponseDto placeOrder(OrderRequestDto orderRequestDto, Item item) throws InvalidCartException, InvalidQuentityException, InvalidCardException;

     OrderResponseDto placeOrderForCartItems(OrderRequestDto orderRequestDto) throws Exception;
}
