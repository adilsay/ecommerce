package com.example.eCommerce.repository;

import com.example.eCommerce.model.Ordered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Ordered,Integer> {
}
