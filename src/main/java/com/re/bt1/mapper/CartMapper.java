package com.re.bt1.mapper;

import com.re.bt1.dto.request.AddCartRequest;
import com.re.bt1.dto.response.CartItemResponse;
import com.re.bt1.entity.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    public CartItem toEntity(AddCartRequest addCartRequest){
        return CartItem.builder()
                .userId(addCartRequest.getUserId())
                .productId(addCartRequest.getProductId())
                .quantity(addCartRequest.getQuantity())
                .build();
    }

    public CartItemResponse toResponse(CartItem cartItem){
        return CartItemResponse.builder()
                .id(cartItem.getId())
                .productId(cartItem.getProductId())
                .quantity(cartItem.getQuantity())
                .build();
    }
}
