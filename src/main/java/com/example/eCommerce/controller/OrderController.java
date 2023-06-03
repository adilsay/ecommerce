package com.example.eCommerce.controller;

import com.example.eCommerce.dto.RequestDto.CardRequestDto;
import com.example.eCommerce.dto.RequestDto.ItemRequestDto;
import com.example.eCommerce.dto.RequestDto.MultipleRequestBodies;
import com.example.eCommerce.dto.RequestDto.OrderRequestDto;
import com.example.eCommerce.dto.ResponseDto.OrderResponseDto;
import com.example.eCommerce.exception.InvalidCardException;
import com.example.eCommerce.exception.InvalidCartException;
import com.example.eCommerce.exception.InvalidQuentityException;
import com.example.eCommerce.model.Cart;
import com.example.eCommerce.model.Item;
import com.example.eCommerce.service.CartService;
import com.example.eCommerce.service.Impl.CartServiceImpl;
import com.example.eCommerce.service.Impl.ItemServiceImpl;
import com.example.eCommerce.service.Impl.OrderServiceImpl;
import jakarta.persistence.criteria.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    ItemServiceImpl itemService;
//    @Data
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public class MultipleRequestBodies{
//        private ItemRequestDto itemRequestDto;
//        private  OrderRequestDto orderRequestDto;
//    }
    @PostMapping("/placeorder")
    public OrderResponseDto placeOrder(@RequestBody MultipleRequestBodies multipleRequestBodies)
            throws Exception {
        ItemRequestDto itemRequestDto = multipleRequestBodies.getItemRequestDto();
        OrderRequestDto orderRequestDto = multipleRequestBodies.getOrderRequestDto();

        Item item = itemService.addItem(itemRequestDto);
        return orderService.placeOrder(orderRequestDto,item);
    }
    @PostMapping("/placeOrderForCartItems")
    public OrderResponseDto placeOrderForCartItems(@RequestBody OrderRequestDto orderRequestDto) throws Exception {
        return orderService.placeOrderForCartItems(orderRequestDto);
    }
}
