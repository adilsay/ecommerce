package com.example.eCommerce.controller;

import com.example.eCommerce.dto.RequestDto.CustomerRequestDto;
import com.example.eCommerce.dto.ResponseDto.CustomerResponseDto;
import com.example.eCommerce.exception.MobileNoAlradyPresentException;
import com.example.eCommerce.service.Impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerServiceImpl customerService;
    @PostMapping("/addCustomer")
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws MobileNoAlradyPresentException {
        return customerService.addCustomer(customerRequestDto);
    }
}
