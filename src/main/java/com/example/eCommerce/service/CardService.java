package com.example.eCommerce.service;

import com.example.eCommerce.dto.RequestDto.CardRequestDto;
import com.example.eCommerce.dto.ResponseDto.CardResponseDto;
import com.example.eCommerce.exception.InvalidCustomerException;
import lombok.*;
import lombok.experimental.FieldDefaults;

public interface CardService {
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws InvalidCustomerException;
}
