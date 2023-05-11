package com.example.eCommerce.controller;

import com.example.eCommerce.dto.RequestDto.ItemRequestDto;
import com.example.eCommerce.dto.ResponseDto.CartResponseDto;
import com.example.eCommerce.model.Item;
import com.example.eCommerce.service.Impl.CartServiceImpl;
import com.example.eCommerce.service.Impl.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ItemServiceImpl itemService;
    @Autowired
    CartServiceImpl cartService;
    @PostMapping("/addToCart")
    public ResponseEntity addCart(@RequestBody ItemRequestDto itemRequestDto){

        try {
            Item savedItem = itemService.addItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.saveCart(savedItem, itemRequestDto.getCustomerId());
            return new ResponseEntity(cartResponseDto,HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
