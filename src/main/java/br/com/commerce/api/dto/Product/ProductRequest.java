package br.com.commerce.api.dto.Product;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotEmpty
    private String description;

    @NotNull
    @DecimalMin(value = "0", message = "Price needs greater 0 (zero)")
    private Double price;

}
