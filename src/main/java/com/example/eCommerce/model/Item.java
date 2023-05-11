package com.example.eCommerce.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    int requiredQuentity;
    @ManyToOne
    @JoinColumn
    Cart cart;
    @ManyToOne
    @JoinColumn
    Ordered order;
    @ManyToOne
    @JoinColumn
    Product product;
}
