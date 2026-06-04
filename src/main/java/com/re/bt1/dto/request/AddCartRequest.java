package com.re.bt1.dto.request;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCartRequest {
    @NotBlank(message = "User id is required")
    private String userId;

    @NotBlank(message = "product id is required")
    private String productId;

    @Min(value = 1, message = "Quantity must be greater than 0")
    @NotNull(message = "Quantity must be greater than 1")
    private Integer quantity;
}
