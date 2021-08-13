package com.shopping.cart.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProductRequest {
    @NotNull
    @Min(1)
    private Long id;

    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Product name does not match pattern.")
    private String name;

    @PositiveOrZero(message = "Product price can not be less then 0!")
    private BigDecimal price;
}
