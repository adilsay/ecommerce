package com.example.eCommerce.model;

import jakarta.persistence.*;
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
@Entity
@Table(name = "ordered")
public class Ordered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String orderNo;
    int totalValue;
    @CreationTimestamp
    Date orderDate;
    String cardUsed;
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    List<Item> itemList = new ArrayList<>();
    @ManyToOne
    @JoinColumn
    Customer customer;
}





