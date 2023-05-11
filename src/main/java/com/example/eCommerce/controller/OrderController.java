package com.example.eCommerce.controller;

import com.example.eCommerce.dto.RequestDto.CardRequestDto;
import com.example.eCommerce.dto.RequestDto.OrderRequestDto;
import com.example.eCommerce.dto.ResponseDto.OrderResponseDto;
import com.example.eCommerce.exception.InvalidCardException;
import com.example.eCommerce.exception.InvalidCartException;
import com.example.eCommerce.exception.InvalidQuentityException;
import com.example.eCommerce.model.Cart;
import com.example.eCommerce.service.CartService;
import com.example.eCommerce.service.Impl.CartServiceImpl;
import com.example.eCommerce.service.Impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderServiceImpl orderService;
//    @PostMapping("/placeorder")
//    public OrderResponseDto placeOrder(@RequestBody OrderRequestDto orderRequestDto) throws InvalidCartException, InvalidQuentityException, InvalidCardException {
//        return orderService.placeOrder(orderRequestDto);
//    }
    @PostMapping("/placeorder2")
    public OrderResponseDto placeOrder2(@RequestBody OrderRequestDto orderRequestDto) throws Exception {
        return orderService.placeOrder2(orderRequestDto);
    }
}
