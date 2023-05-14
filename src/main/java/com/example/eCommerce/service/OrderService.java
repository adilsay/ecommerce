package com.example.eCommerce.service;

import com.example.eCommerce.dto.RequestDto.OrderRequestDto;
import com.example.eCommerce.dto.ResponseDto.OrderResponseDto;
import com.example.eCommerce.exception.InvalidCardException;
import com.example.eCommerce.exception.InvalidCartException;
import com.example.eCommerce.exception.InvalidQuentityException;

public interface OrderService {
     OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws InvalidCartException, InvalidQuentityException, InvalidCardException;

     OrderResponseDto placeOrderForCartItems(OrderRequestDto orderRequestDto) throws Exception;
}
