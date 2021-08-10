package com.shopping.cart.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class DeleteCartItemRequest {

    @NotNull
    @Min(1)
    private Long personId;

    @NotNull
    @Min(1)
    private Long cartItemId;
}
