package br.com.commerce.api.dto.Order;

import java.time.Instant;
import java.util.List;

import br.com.commerce.api.dto.User.UserResponse;
import br.com.commerce.api.models.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Long id;
    private String state;
    private UserResponse user;
    private List<OrderItem> orderItems;
    private Instant dateCreate;
    private Instant dateUpdate;
}
