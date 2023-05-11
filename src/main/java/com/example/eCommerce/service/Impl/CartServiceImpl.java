package com.example.eCommerce.service.Impl;

import com.example.eCommerce.dto.ResponseDto.CartResponseDto;
import com.example.eCommerce.exception.InvalidCartException;
import com.example.eCommerce.model.Cart;
import com.example.eCommerce.model.Customer;
import com.example.eCommerce.model.Item;
import com.example.eCommerce.repository.CartRepository;
import com.example.eCommerce.repository.CustomerRepository;
import com.example.eCommerce.service.CartService;
import com.example.eCommerce.transformer.CartTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CartRepository cartRepository;
    @Override
    public CartResponseDto saveCart(Item item, Integer customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        Cart cart = customer.getCart();

        int newTotal = cart.getTotalCost()+item.getRequiredQuentity()*item.getProduct().getPrice();
//        cart.getItems().add(item); // this was the bug which adding extra item.
//        int newNumberOfItems = cart.getNumberOfItems() + item.getRequiredQuentity();
        cart.setTotalCost(newTotal);
        cart.setNumberOfItems(cart.getItems().size());
        Cart savedCart = cartRepository.save(cart);

//        prepare response dto
        CartResponseDto cartResponseDto = CartTransformer.CartToCartResponseDto(cart);
        return cartResponseDto;

    }

    @Override
    public void isValidCartToPlaceOrder(int cartId) throws InvalidCartException {

            Cart cart = cartRepository.findById(cartId).get();
            if(cart.getNumberOfItems() == 0)
                throw new InvalidCartException("Cart Is Not valid");


    }
}
