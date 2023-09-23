package br.com.commerce.api.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.commerce.api.dto.order.OrderMapper;
import br.com.commerce.api.dto.order.orderitem.OrderItemRequest;
import br.com.commerce.api.dto.order.orderitem.OrderItemResponse;
import br.com.commerce.api.dto.order.orderitem.OrderItemsMapper;
import br.com.commerce.api.models.Order;
import br.com.commerce.api.models.OrderItem;
import br.com.commerce.api.repositories.OrderItemRepository;

@Service
public class OrderItemsService {

    @Autowired
    private OrderItemRepository orderItemsRepository;

    @Autowired
    private OrderItemsMapper mapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderMapper orderMapper;

    public OrderItemResponse save(OrderItemRequest item) {
        OrderItem orderItem = mapper.toOrderItem(item);
        return mapper.toOrderItemResponse(orderItemsRepository.save(orderItem));
    }

    public void deleteOrderItems(Long id) {
        if (orderItemsRepository.findById(id).isPresent()) {
            orderItemsRepository.deleteById(id);
        }
    }

    public OrderItemResponse updateOrderItems(OrderItemRequest item, Long id) {
        OrderItem orderItem = mapper.toOrderItem(item);
        if (orderItemsRepository.findById(id).isPresent()) {
            orderItem.setId(id);
            return mapper.toOrderItemResponse(orderItemsRepository.save(orderItem));
        }
        return null;
    }

    public List<OrderItemResponse> findAllOrderItems() {
        return mapper.toListOrderItemResponse(orderItemsRepository.findAll());
    }

    public List<OrderItemResponse> findAllOrderItemsByOrderId(Long orderId) {
        Order order = orderMapper.toOrder(orderService.findById(orderId));
        return mapper.toListOrderItemResponse(
            orderItemsRepository.findByOrder(order));
    }

    public OrderItemResponse findById(Long id) {
         return mapper.toOrderItemResponse(orderItemsRepository.findById(id));
    }

}
