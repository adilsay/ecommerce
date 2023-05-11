package com.example.eCommerce.dto.RequestDto;

import com.example.eCommerce.Enum.ProductCategory;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {
    int sellerId;
    String productName;
    int price;
    int quantity;
    ProductCategory productCategory;

}
