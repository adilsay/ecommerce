package com.example.eCommerce.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    @Column(unique = true)
    String emailId;
    Integer age;
    @Column(unique = true)
    String mobNo;
    String addr;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<Card> cards = new ArrayList<>();
    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    Cart cart;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<Ordered> orderList = new ArrayList<>();
}


