package com.re.bt1.service.impl;

import com.re.bt1.dto.request.AddCartRequest;
import com.re.bt1.dto.request.UpdateCartRequest;
import com.re.bt1.dto.response.CartItemResponse;
import com.re.bt1.entity.CartItem;
import com.re.bt1.exception.ResourceNotFoundException;
import com.re.bt1.mapper.CartMapper;
import com.re.bt1.repository.CartRepository;
import com.re.bt1.service.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartMapper mapper;

    @Override
    public CartItemResponse addCartItem(AddCartRequest request) {

        CartItem item = mapper.toEntity(request);
        CartItem save = cartRepository.save(item);
        return mapper.toResponse(save);
    }

    @Override
    public List<CartItemResponse> getCartByUserId(String userId) {
        List<CartItem> items = cartRepository.findByUserId(userId);
        if(items.isEmpty()){
            throw new ResourceNotFoundException("Cart not found");
        }
            return items
                    .stream()
                    .map(mapper::toResponse)
                    .toList();
    }

    @Override
    public CartItemResponse updateCartItem(Long id, UpdateCartRequest cartItem) {
        CartItem cartItem1 = cartRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Cart item not found with id: " + id));
        cartItem1.setQuantity(cartItem.getQuantity());
        CartItem updated = cartRepository.save(cartItem1);
        return mapper.toResponse(updated);
    }

    @Override
    public void deleteCartItem(Long cartItemId) {
        Optional<CartItem> cartItem = cartRepository.findById(cartItemId);
        if(cartItem.isEmpty()){
           throw new ResourceNotFoundException("Cart item not found");
        }
        cartRepository.delete(cartItem.get());
    }

    @Override
    public CartItemResponse getCartItemById(Long id) {
        CartItem cartItem = cartRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Cart not found"));

        return mapper.toResponse(cartItem);
    }

    @Override
    public List<CartItemResponse> getMyCart() {
        return List.of();
    }
}
