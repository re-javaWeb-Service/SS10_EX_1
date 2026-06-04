package com.re.bt1.controller;


import com.re.bt1.dto.ApiResponse;
import com.re.bt1.dto.request.AddCartRequest;
import com.re.bt1.dto.request.UpdateCartRequest;
import com.re.bt1.dto.response.CartItemResponse;
import com.re.bt1.entity.CartItem;
import com.re.bt1.mapper.CartMapper;
import com.re.bt1.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//POST   /api/cart
//GET    /api/cart/{userId}
//DELETE /api/cart/{id}
@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService service;

    @PostMapping
    public ResponseEntity<ApiResponse<CartItemResponse>> addCartRequestResponseEntity(
            @Valid @RequestBody AddCartRequest request
    ){
        CartItemResponse response = service.addCartItem(request);
         return ResponseEntity.status(HttpStatus.CREATED)
                 .body(
                         ApiResponse.<CartItemResponse>builder()
                                 .code(HttpStatus.CREATED.value())
                                 .success(true)
                                 .message("Add Cart Successfully")
                                 .data(response)
                                 .build()
                 );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<List<CartItemResponse>>> getListCart(
            @PathVariable String userId
    ) {

        List<CartItemResponse> list =
                service.getCartByUserId(userId);

        return ResponseEntity.ok(
                ApiResponse.<List<CartItemResponse>>builder()
                        .success(true)
                        .code(HttpStatus.OK.value())
                        .message("Get list successfully")
                        .data(list)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCart(@PathVariable Long id){
        service.deleteCartItem(id);
        return ResponseEntity.ok(
                ApiResponse.<Void>builder()
                        .success(true)
                        .code(HttpStatus.OK.value())
                        .message("Deleted successfully")
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CartItemResponse>> updateCartItem(@Valid @RequestBody UpdateCartRequest cartRequest, @PathVariable Long id){
        CartItemResponse response= service.updateCartItem(id,cartRequest);
        return ResponseEntity.ok(
                ApiResponse.<CartItemResponse>builder()
                        .code(HttpStatus.OK.value())
                        .data(response)
                        .message("Updated Successfully")
                        .success(true)
                        .build()
        );
    }


}
