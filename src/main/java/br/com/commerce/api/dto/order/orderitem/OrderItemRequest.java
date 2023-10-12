package br.com.commerce.api.dto.order.orderitem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItemRequest {

    private Long id;
    private Long order;
    private Long product;
    private double price;
    private int quantity;

}
