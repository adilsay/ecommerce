package com.example.eCommerce.service.Impl;

import com.example.eCommerce.Enum.ProductStatus;
import com.example.eCommerce.dto.RequestDto.OrderRequestDto;
import com.example.eCommerce.dto.ResponseDto.OrderResponseDto;
import com.example.eCommerce.exception.InvalidCardException;
import com.example.eCommerce.exception.InvalidCartException;
import com.example.eCommerce.exception.InvalidCustomerException;
import com.example.eCommerce.exception.InvalidQuentityException;
import com.example.eCommerce.model.*;
import com.example.eCommerce.repository.CardRepository;
import com.example.eCommerce.repository.CartRepository;
import com.example.eCommerce.repository.CustomerRepository;
import com.example.eCommerce.repository.OrderRepository;
import com.example.eCommerce.service.ItemService;
import com.example.eCommerce.service.OrderService;
import com.example.eCommerce.transformer.CardTransformer;
import com.example.eCommerce.transformer.CartTransformer;
import com.example.eCommerce.transformer.ItemTransformer;
import com.example.eCommerce.transformer.OrderTransformer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto,Item item)
            throws InvalidCartException, InvalidQuentityException, InvalidCardException {

//      chack item is valid to order
        if(item.getRequiredQuentity() > item.getProduct().getQuantity() ||
        item.getProduct().getProductStatus().equals(ProductStatus.OUT_OF_STOCK))
            throw new InvalidQuentityException("Quantity Not Available Or Product Out of stock");

        updateQuentity(item);
        Customer customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();

        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
        if(card == null || card.getCvv() != orderRequestDto.getCvv() ||
           card.getExpiryDate().before(new Date()) ||
           card.getCustomer() != card.getCustomer()) {
            throw new InvalidCardException("Card is Not Valid !!!" + card.getCardNo());
        }


        Ordered order = OrderTransformer.itemToOrderTransformer(item,customer);
        order.setCardUsed(card.getCardNo());
        ItemTransformer.setOrderId(order);
        customer.getOrderList().add(order);

        Ordered savedOrder = orderRepository.save(order);
//        savedOrder.getCustomer().getOrderList().add(order);

        //prepare response dto
        return OrderTransformer.OrderToOrderResponseDTO(savedOrder);

    }


    @Override
    public OrderResponseDto placeOrderForCartItems(OrderRequestDto orderRequestDto) throws Exception {
        Ordered ordered;
        Cart cart;
        Card card;
        Customer customer;
        //getting customer from id
        try{
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
            cart = customer.getCart();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer Id is Ivalid !!!");
        }

        // check is cart empty and validate every item by updating the product value
        System.out.println("Is Cart Empty : "+cart.getItems().isEmpty()+" "+ cart.getItems().size());
        if(cart.getItems().isEmpty())
            throw new InvalidCartException("Cart Is Empty");

        for(Item item: cart.getItems()){
            boolean isProductOutOfStock = item.getProduct().getProductStatus().equals(ProductStatus.OUT_OF_STOCK);
            boolean isQuentityUnavailable = item.getRequiredQuentity() > item.getProduct().getQuantity();
                if(isProductOutOfStock || isQuentityUnavailable) {
                    throw new InvalidQuentityException("Product dosen't has required quentity !!!!");
                }
            updateQuentity(item);
        }

        // verify card provided by customer from db
        try{
            card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
            if(card == null || !(card.getCardNo().equals(orderRequestDto.getCardNo()))
                    || card.getCvv() != orderRequestDto.getCvv()
                    || card.getExpiryDate().before(new Date()))
                throw new InvalidCardException("Invalid card details has been enterded ");
        }
        catch (Exception e){
            throw new InvalidCardException(e.getMessage());
        }
        // prepare order and add it to custimer ordder list
            ordered = OrderTransformer.cartToOrderTransformer(cart);
            ordered.setCardUsed(card.getCardNo());
            ordered.getCustomer().getOrderList().add(ordered);
            ItemTransformer.setOrderId(ordered);

        // save the order
        try {
            //empty the cart
            CartTransformer.emptyTheCart(cart);
            cartRepository.save(cart);
            orderRepository.save(ordered);
        }
        catch (Exception e){
            throw new Exception(e.getMessage()+" Got Caught While Saving");
        }

        // prepare response dto
        return OrderTransformer.OrderToOrderResponseDTO(ordered);
    }

    public void updateQuentity(Item item){
        int quentity = item.getProduct().getQuantity();
        int requiredQuentity = item.getRequiredQuentity();
        item.getProduct().setQuantity(quentity-requiredQuentity);
        if(item.getProduct().getQuantity() == 0)
            item.getProduct().setProductStatus(ProductStatus.OUT_OF_STOCK);
    }
}
