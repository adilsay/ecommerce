package com.example.eCommerce.repository;

import com.example.eCommerce.Enum.ProductCategory;
import com.example.eCommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    List<Product> findByProductCategory(ProductCategory productCategory);

//    List<Product> findByPrice(int price);

    @Query(value = "select p from Product p where p.price > :price and p.productCategory=:category")
    List<Product> findByPriceAndCategory(int price, ProductCategory category);
}
