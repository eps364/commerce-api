package br.com.commerce.api.dto.order;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.commerce.api.models.Order;


@Component
public class OrderMapper {
    @Autowired
    private ModelMapper mapper;

    public Order toOrder(OrderRequest order) {
        return mapper.map(order, Order.class);
    }

    public Order toOrder(OrderResponse order) {
        return mapper.map(order, Order.class);
    }

    public OrderRequest toOrderRequest(Order order) {
        return mapper.map(order, OrderRequest.class);
    }

    public OrderResponse toOrderResponse(Order order) {
        return mapper.map(order, OrderResponse.class);
    }

    public OrderResponse toOrderResponse(Optional<Order> order) {
        return mapper.map(order, OrderResponse.class);
    }

    public List<OrderResponse> toListOrderResponse(List<Order> listOrder) {
        return listOrder.stream()
                .map(this::toOrderResponse)
                .toList();
    }
}
