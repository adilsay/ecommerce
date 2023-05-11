package com.example.eCommerce.service.Impl;

import com.example.eCommerce.dto.RequestDto.CustomerRequestDto;
import com.example.eCommerce.dto.ResponseDto.CustomerResponseDto;
import com.example.eCommerce.exception.MobileNoAlradyPresentException;
import com.example.eCommerce.model.Cart;
import com.example.eCommerce.model.Customer;
import com.example.eCommerce.repository.CustomerRepository;
import com.example.eCommerce.service.CustomerService;
import com.example.eCommerce.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
   @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) throws MobileNoAlradyPresentException {

        if(customerRepository.findByMobNo(customerRequestDto.getMobNo()) != null)
            throw new MobileNoAlradyPresentException("Customer Alrady Exist try using another mobile number");
        Customer customer= CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);
        Cart cart = Cart.builder()
                .customer(customer)
                .totalCost(0)
                .numberOfItems(0)
                .build();
        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer); // customer and cart

        // prepare response dto
        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);

    }
}
