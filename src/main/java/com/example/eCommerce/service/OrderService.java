package com.example.eCommerce.service;

import com.example.eCommerce.dto.RequestDto.OrderRequestDto;
import com.example.eCommerce.dto.ResponseDto.OrderResponseDto;
import com.example.eCommerce.exception.InvalidCardException;
import com.example.eCommerce.exception.InvalidCartException;
import com.example.eCommerce.exception.InvalidQuentityException;

public interface OrderService {
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws InvalidCartException, InvalidQuentityException, InvalidCardException;

    OrderResponseDto placeOrder2(OrderRequestDto orderRequestDto) throws Exception;
}
