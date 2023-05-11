package com.example.eCommerce.dto.ResponseDto;

import com.example.eCommerce.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {
    String productName;
    String sallerName;
    int quantity;
    ProductStatus productStatus;

}
