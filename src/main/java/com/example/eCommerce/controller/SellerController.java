package com.example.eCommerce.controller;

import com.example.eCommerce.Enum.ProductCategory;
import com.example.eCommerce.dto.RequestDto.SellerRequestDto;
import com.example.eCommerce.dto.ResponseDto.ProductResponseDto;
import com.example.eCommerce.dto.ResponseDto.SellerResponseDto;
import com.example.eCommerce.model.Seller;
import com.example.eCommerce.service.Impl.SellerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerServiceImpl sellerService;
    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto) {

        try {
            SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);
            return new ResponseEntity(sellerResponseDto, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/getSeller/{category}")
    public List<SellerResponseDto> getSellerByProductCategory(@PathVariable("category")
                                                                  ProductCategory category){
            return sellerService.getSellerByProductCategory(category);

    }
}
