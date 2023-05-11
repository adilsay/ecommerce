package com.example.eCommerce.service.Impl;

import com.example.eCommerce.dto.RequestDto.CardRequestDto;
import com.example.eCommerce.dto.ResponseDto.CardResponseDto;
import com.example.eCommerce.exception.InvalidCustomerException;
import com.example.eCommerce.model.Card;
import com.example.eCommerce.model.Customer;
import com.example.eCommerce.repository.CustomerRepository;
import com.example.eCommerce.service.CardService;
import com.example.eCommerce.transformer.CardTransformer;
import com.example.eCommerce.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException {

        Customer customer = customerRepository.findByMobNo(cardRequestDto.getMobNo());

        if(customer == null)
            throw new InvalidCustomerException("Sorry Customer Does Not Exist !!!");

        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);

            customer.getCards().add(card); // adding card to the list of the customer
            customerRepository.save(customer); // custoemr and card both will saved in Db

//        prepare response
        return CardTransformer.CardToCardResponseDto(card);
    }
}
