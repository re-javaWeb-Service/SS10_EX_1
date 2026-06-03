package com.re.bt1.service;

import com.re.bt1.dto.request.AddCartRequest;
import com.re.bt1.dto.response.CartItemResponse;
import com.re.bt1.entity.CartItem;

import java.util.List;

public interface CartService {
    CartItemResponse  addCartItem(AddCartRequest request);
    List<CartItemResponse> getCartByUserId(String userId);
    CartItemResponse updateCartItem(Long id, AddCartRequest cartItem);
     void deleteCartItem(Long cartItemId);
     CartItemResponse getCartItemById(Long id);
    List<CartItemResponse> getMyCart();
}
