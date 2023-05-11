package com.example.eCommerce.transformer;

import com.example.eCommerce.dto.RequestDto.CustomerRequestDto;
import com.example.eCommerce.dto.ResponseDto.CustomerResponseDto;
import com.example.eCommerce.model.Cart;
import com.example.eCommerce.model.Customer;

public class CustomerTransformer {
    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){


        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .addr(customerRequestDto.getAddr())
                .mobNo(customerRequestDto.getMobNo())
                .emailId(customerRequestDto.getEmailId())
                .build();
    }

    public static CustomerResponseDto CustomerToCustomerResponseDto(Customer customer){


        return CustomerResponseDto.builder()
                .name(customer.getName())
                .message("WelCome "+customer.getName()+" to eCommerce !!!")
                .build();
    }
}
