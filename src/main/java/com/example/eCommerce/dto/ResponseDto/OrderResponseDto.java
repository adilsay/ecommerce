package com.example.eCommerce.dto.ResponseDto;

import com.example.eCommerce.model.Customer;
import com.example.eCommerce.model.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {
    String orderId;
    int totalValue;
    Date orderDate;
    String cardUsed;
    List<ItemResponseDto> items = new ArrayList<>();
    String customerName;
}
