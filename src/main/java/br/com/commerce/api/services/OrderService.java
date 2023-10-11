package br.com.commerce.api.services;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.commerce.api.dto.order.OrderMapper;
import br.com.commerce.api.dto.order.OrderRequest;
import br.com.commerce.api.dto.order.OrderResponse;
import br.com.commerce.api.dto.user.UserMapper;
import br.com.commerce.api.dto.user.UserResponse;
import br.com.commerce.api.models.Order;
import br.com.commerce.api.models.OrderState;
import br.com.commerce.api.models.User;
import br.com.commerce.api.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Cacheable("OrderService.findAllOrders")
    public List<OrderResponse> findAllOrders() {
        log.info(this.getClass().getName() + " | " + "findAllOrders");
        return orderMapper.toListOrderResponse(orderRepository.findAll());
    }

    @Cacheable("OrderService.findById")
    public OrderResponse findById(Long id) {
        log.info(this.getClass().getName() + " | " + "findById");
        return orderMapper.toOrderResponse(orderRepository.findById(id));
    }

    @Cacheable("OrderService.findByUser")
    public List<OrderResponse> findByUser(String id) {
        log.info(this.getClass().getName() + " | " + "findByUser");
        User user = userService.findByUserId(id);
        return orderMapper.toListOrderResponse(orderRepository.findByUser(user));
    }

    @Transactional
    public OrderResponse save(OrderRequest order) {
        UserResponse userResponse = userService.findById(UUID.fromString(order.getUser()));
        if (userResponse != null) {
            User user = userMapper.toUser(userResponse);
            Order orderSave = Order.builder()
                    .user(user)
                    .dateCreate(Instant.now())
                    .dateUpdate(Instant.now())
                    .state(OrderState.INCOMPLETE)
                    .build();
            Order orderSaved = orderRepository.save(orderSave);
            return orderMapper.toOrderResponse(orderSaved);
        }

        return null;

    }

}
