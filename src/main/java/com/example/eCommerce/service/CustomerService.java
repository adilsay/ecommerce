package com.example.eCommerce.service;

import com.example.eCommerce.dto.RequestDto.CustomerRequestDto;
import com.example.eCommerce.dto.ResponseDto.CustomerResponseDto;
import com.example.eCommerce.exception.MobileNoAlradyPresentException;
import org.springframework.web.bind.annotation.RequestBody;

public interface CustomerService {
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws MobileNoAlradyPresentException;
}
