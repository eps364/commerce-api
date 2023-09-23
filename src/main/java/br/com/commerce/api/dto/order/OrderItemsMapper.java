package br.com.commerce.api.dto.order;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.commerce.api.models.OrderItem;

@Component
public class OrderItemsMapper {

    @Autowired
    private ModelMapper mapper;

    public OrderItem toOrderItem(OrderItemRequest item) {
        return mapper.map(item, OrderItem.class);
    }

    public OrderItem toOrderItem(OrderItemResponse item) {
        return mapper.map(item, OrderItem.class);
    }

    public OrderItemRequest toOrderItemRequest(OrderItem item) {
        return mapper.map(item, OrderItemRequest.class);
    }

    public OrderItemResponse toOrderItemResponse(OrderItem item) {
        return mapper.map(item, OrderItemResponse.class);
    }

    public OrderItemResponse toOrderItemResponse(Optional<OrderItem> item) {
        return mapper.map(item, OrderItemResponse.class);
    }

    public List<OrderItemResponse> toListOrderItemResponse(List<OrderItem> listOrderItem) {
        return listOrderItem.stream()
                .map(this::toOrderItemResponse)
                .toList();
    }


}
