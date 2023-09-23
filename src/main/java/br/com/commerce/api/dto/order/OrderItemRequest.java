package br.com.commerce.api.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {

    private Long id;
    private Long order;
    private Long product;
    private double price;
    private int quantity;

}
