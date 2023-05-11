package com.example.eCommerce.dto.ResponseDto;

import com.example.eCommerce.model.Item;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartResponseDto {
    Integer totalCost;
    Integer numberOfItems;
    String customerName;
    List<ItemResponseDto> items;
}
