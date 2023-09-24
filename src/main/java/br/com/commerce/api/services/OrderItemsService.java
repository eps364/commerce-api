package br.com.commerce.api.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.commerce.api.dto.order.OrderMapper;
import br.com.commerce.api.dto.order.OrderResponse;
import br.com.commerce.api.dto.order.orderitem.OrderItemRequest;
import br.com.commerce.api.dto.order.orderitem.OrderItemRequestPut;
import br.com.commerce.api.dto.order.orderitem.OrderItemResponse;
import br.com.commerce.api.dto.order.orderitem.OrderItemsMapper;
import br.com.commerce.api.dto.product.ProductMapper;
import br.com.commerce.api.models.Order;
import br.com.commerce.api.models.OrderItem;
import br.com.commerce.api.models.Product;
import br.com.commerce.api.repositories.OrderItemRepository;
import jakarta.transaction.Transactional;

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

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    @Transactional
    public OrderItemResponse save(OrderItemRequest item) {
        Order order = orderMapper.toOrder(orderService.findById(item.getOrder()));
        Product product = productMapper.toProduct(productService.findById(item.getProduct()));
        if (order != null) {
            OrderItem orderItemSave = OrderItem.builder()
                    .order(order)
                    .product(product.getId())
                    .price(product.getPrice())
                    .quantity(item.getQuantity())
                    .build();
            return mapper.toOrderItemResponse(orderItemsRepository.save(orderItemSave));
        }
        return null;
    }

    @Transactional
    public void deleteOrderItems(Long id) {
        if (orderItemsRepository.findById(id).isPresent()) {
            orderItemsRepository.deleteById(id);
        }
    }

    @Transactional
    public OrderItemResponse updateOrderItems(OrderItemRequestPut item, Long id_item) {
        Optional<OrderItem> orderitem =  orderItemsRepository.findById(id_item);
        if (orderitem.isPresent()) {
            orderitem.get().setQuantity(item.getQuantity());
            return mapper.toOrderItemResponse(orderItemsRepository.save(orderitem.get()));
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
