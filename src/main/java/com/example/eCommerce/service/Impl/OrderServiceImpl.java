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
import com.example.eCommerce.transformer.OrderTransformer;
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
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto)
            throws InvalidCartException, InvalidQuentityException, InvalidCardException {

//        Cart cart = cartRepository.findById(orderRequestDto.getCartId()).get();

        Customer customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
        Cart cart = customer.getCart();
        if(cart.getNumberOfItems() == 0 || cart == null) {
            throw new InvalidCartException("Cart is Empty !!!");
        }
        for(Item item: cart.getItems()){
            if(item.getRequiredQuentity() > item.getProduct().getQuantity())
                throw new InvalidQuentityException("Product not has required quintity - "+
                        item.getRequiredQuentity());
        }

        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
        if(card == null || card.getCvv() != orderRequestDto.getCvv() ||
           card.getExpiryDate().before(new Date()) ||
           card.getCustomer() != cart.getCustomer()) {
            throw new InvalidCardException("Card is Not Valid !!!");
        }


        Ordered order = OrderTransformer.cartToOrderTransformer(cart);
        order.setCardUsed(CardTransformer.maskCardNumber(card.getCardNo()));
        Ordered savedOrder = orderRepository.save(order);
        savedOrder.getCustomer().getOrderList().add(order);
        // empty the cart
//        cartRepository.save(Cart.builder()
//                                .items(new ArrayList<>())
//                                .totalCost(0)
//                                .numberOfItems(0)
//                                .build());

        cart.getItems().clear(); // after order is placed empty the list.
        cart.setTotalCost(0);
        cart.setNumberOfItems(0);

        for(Item item:savedOrder.getItemList()){
            updateQuentity(item);
        }

        //prepare response dto
        return OrderTransformer.OrderToOrderResponseDTO(order);

    }

    @Override
    public OrderResponseDto placeOrder2(OrderRequestDto orderRequestDto) throws Exception {
        Ordered ordered;
        Cart cart = null;
        Card card = null;
        Customer customer;
        //getting customer from id
        try{
            customer = customerRepository.findById(orderRequestDto.getCustomerId()).get();
            cart = customer.getCart();
        }
        catch (Exception e){
            throw new InvalidCustomerException("Customer Id is Ivalid !!!");
        }
        // check cart and  validate every item by updating the product value
        try{
//            cart = cartRepository.findById(orderRequestDto.getCartId()).get();
            if(cart.getItems().isEmpty())
                throw new InvalidCartException("Cart Is Empty");
            for(Item item: cart.getItems()){
                if(item.getRequiredQuentity() > item.getProduct().getQuantity())
                    throw new InvalidQuentityException("Product dosen't has required quentity !!!!");
                updateQuentity(item);
            }
        }
        catch (Exception e){
            new InvalidCartException("Cart Id is Not Valid");
        }

        // verify card provided by customer from db
        try{
            card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
            if(card.getCardNo() != orderRequestDto.getCardNo() || card.getCvv() != orderRequestDto.getCvv())
                throw new InvalidCardException("Invalid card details has been enterded ");
        }
        catch (Exception e){
            new InvalidCardException("Card Is Not Present");
        }
        // prepare order and add
        try {
            ordered = OrderTransformer.cartToOrderTransformer(cart);
            ordered.setCardUsed(card.getCardNo());
          ordered.getCustomer().getOrderList().add(ordered);
        }
        catch (Exception e){
            throw new Exception(e.getMessage()+" Got caught before save");
        }
        // save the order
        try {
//           customer.getOrderList().add(ordered);
           orderRepository.save(ordered);
        }
        catch (Exception e){
            throw new Exception(e.getMessage()+" Got Caught While Saving");
        }
//      empty the cart
        cart.setNumberOfItems(0);
        cart.setTotalCost(0);
        cart.setItems(new ArrayList<>());

        // prepare response dto
        try {
            OrderResponseDto orderResponseDto = OrderTransformer.OrderToOrderResponseDTO(ordered);
            return orderResponseDto;
        }
        catch (Exception e){
            throw new Exception(e.getMessage()+" Got caught while prepaing response !!!!");
        }



//        return OrderResponseDto.builder()
//                .orderId("123")
//                .orderDate(new Date())
//                .items(new ArrayList<>())
//                .totalValue(0)
//                .customerName("xxxxx")
//                .cardUsed("NA")
//                .build();
    }

    public void updateQuentity(Item item){
        int quentity = item.getProduct().getQuantity();
        int requiredQuentity = item.getRequiredQuentity();
        item.getProduct().setQuantity(quentity-requiredQuentity);
        if(item.getProduct().getQuantity() == 0)
            item.getProduct().setProductStatus(ProductStatus.OUT_OF_STOCK);
    }
}
