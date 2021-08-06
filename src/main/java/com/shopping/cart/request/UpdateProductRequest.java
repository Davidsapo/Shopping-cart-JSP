package com.shopping.cart.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
public class UpdateProductRequest {

    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Product name does not match pattern.")
    private String name;

    @PositiveOrZero(message = "Product price can not be less then 0!")
    private BigDecimal price;
}
