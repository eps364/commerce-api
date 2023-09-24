package br.com.commerce.api.dto.order.orderitem;

import br.com.commerce.api.util.ConvertAndRound;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {

    private Long id;
    private Long product;
    private double price;
    private int quantity;
    public double getSubtotal(){
        return ConvertAndRound.convert(this.quantity * this.price);
    }

}
