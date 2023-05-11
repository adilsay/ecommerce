package com.example.eCommerce.dto.RequestDto;

import com.example.eCommerce.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderRequestDto {
    int customerId;
    String cardNo;
    int cvv;
    CardType cardType;
}
