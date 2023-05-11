package com.example.eCommerce.service.Impl;

import com.example.eCommerce.Enum.ProductStatus;
import com.example.eCommerce.dto.RequestDto.ItemRequestDto;
import com.example.eCommerce.exception.InvalidCustomerException;
import com.example.eCommerce.exception.InvalidProductException;
import com.example.eCommerce.model.Customer;
import com.example.eCommerce.model.Item;
import com.example.eCommerce.model.Product;
import com.example.eCommerce.repository.CustomerRepository;
import com.example.eCommerce.repository.ItemRepository;
import com.example.eCommerce.repository.ProductRepository;
import com.example.eCommerce.service.ItemService;
import com.example.eCommerce.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ItemRepository itemRepository;
    @Override
    public Item addItem(ItemRequestDto itemRequestDto) throws Exception {
        Customer customer;
        try{
            customer = customerRepository.findById(itemRequestDto.getCustomerId()).get();
        } catch (Exception e){
            throw new InvalidCustomerException("Customer Id Is Invalid");
        }
        Product product;
        try{
            product = productRepository.findById(itemRequestDto.getProductId()).get();
        }catch (Exception e){
            throw new InvalidProductException("Product Not Found !!!");
        }
        if(itemRequestDto.getRequiredQuantity() > product.getQuantity() ||
        product.getProductStatus() != ProductStatus.AVAILABLE){
            throw new Exception("Product OUT OF STOCK !!!");
        }
//        if(!customer.getCart().getItems().isEmpty()){
//            System.out.print(customer.getCart().getItems().isEmpty());
//            for(Item inItem:customer.getCart().getItems()){
//                if(inItem.getProduct().getId() == product.getId())
//                    return inItem;
//            }
//        }
        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto);
        item.setCart(customer.getCart());
        item.setProduct(product);

        product.getItems().add(item);
        Product savedProduct = productRepository.save(product);
//this is the alternate way to return the item
//        int size = product.getItems().size();
//        return savedProduct.getItems().get(size-1);
//        return itemRepository.save(item);
        return item;
    }
}
