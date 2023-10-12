package br.com.commerce.api.dto.order;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import br.com.commerce.api.dto.order.orderitem.OrderItemResponse;
import br.com.commerce.api.dto.user.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse implements Serializable {

    private Long id;
    private String state;
    private UserResponse user;
    private List<OrderItemResponse> orderItems;
    private Instant dateCreate;
    private Instant dateUpdate;
}
