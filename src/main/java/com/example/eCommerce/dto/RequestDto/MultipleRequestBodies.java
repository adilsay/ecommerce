package com.example.eCommerce.dto.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultipleRequestBodies {
    private ItemRequestDto itemRequestDto;
    private  OrderRequestDto orderRequestDto;
}
