package br.com.commerce.api.services;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.commerce.api.dto.Order.OrderMapper;
import br.com.commerce.api.dto.Order.OrderRequest;
import br.com.commerce.api.dto.Order.OrderResponse;
import br.com.commerce.api.dto.User.UserMapper;
import br.com.commerce.api.dto.User.UserResponse;
import br.com.commerce.api.models.Order;
import br.com.commerce.api.models.OrderState;
import br.com.commerce.api.models.User;
import br.com.commerce.api.repositories.OrderRepository;
import jakarta.transaction.Transactional;

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

    public List<OrderResponse> findAllOrders() {
        return orderMapper.toListOrderResponse(orderRepository.findAll());
    }

    public OrderResponse findById(Long id) {
        return orderMapper.toOrderResponse(orderRepository.findById(id));
    }

    public List<OrderResponse> findByUser(String id) {
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
