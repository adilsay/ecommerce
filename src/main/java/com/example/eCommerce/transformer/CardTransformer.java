package com.example.eCommerce.transformer;

import com.example.eCommerce.dto.RequestDto.CardRequestDto;
import com.example.eCommerce.dto.ResponseDto.CardResponseDto;
import com.example.eCommerce.model.Card;
import com.example.eCommerce.model.Customer;

public class CardTransformer {
    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .expiryDate(cardRequestDto.getExpiryDate())
                .cvv(cardRequestDto.getCvv())
                .build();
    }

    public static CardResponseDto CardToCardResponseDto(Card card) {
        return CardResponseDto.builder()
                .customerName(card.getCustomer().getName())
                .cardNo(card.getCardNo())
                .build();
    }
    public static String maskCardNumber(String cardNumber) {
        int visibleDigits = 4;
        int totalDigits = cardNumber.length();

        StringBuilder maskedNumber = new StringBuilder(cardNumber);
        for (int i = 0; i < totalDigits - visibleDigits; i++) {
            maskedNumber.setCharAt(i, 'X');
        }

        return maskedNumber.toString();
    }

}
