package br.com.commerce.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.commerce.api.dto.order.OrderMapper;
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
import lombok.extern.log4j.Log4j2;

@Log4j2
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
    public OrderItemResponse updateOrderItems(OrderItemRequestPut item, Long idItem) {
        Optional<OrderItem> orderitem =  orderItemsRepository.findById(idItem);
        if (orderitem.isPresent()) {
            orderitem.get().setQuantity(item.getQuantity());
            return mapper.toOrderItemResponse(orderItemsRepository.save(orderitem.get()));
        }
        return null;
    }

    @Cacheable("OrderItemsService.findAllOrderItems")
    public List<OrderItemResponse> findAllOrderItems() {
        log.info(this.getClass().getName() + " | " + "findAllOrderItems");
        return mapper.toListOrderItemResponse(orderItemsRepository.findAll());
    }

    @Cacheable("OrderItemsService.findAllOrderItemsByOrderId")
    public List<OrderItemResponse> findAllOrderItemsByOrderId(Long orderId) {
        log.info(this.getClass().getName() + " | " + "findAllOrderItemsByOrderId");
        Order order = orderMapper.toOrder(orderService.findById(orderId));
        return mapper.toListOrderItemResponse(
                orderItemsRepository.findByOrder(order));
    }

    @Cacheable("OrderItemsService.findById")
    public OrderItemResponse findById(Long id) {
        log.info(this.getClass().getName() + " | " + "findById");
        return mapper.toOrderItemResponse(orderItemsRepository.findById(id));
    }

}
