package br.com.commerce.api.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
    private static final String CACHE_FIND_ALL = "OrderService.findAllOrders";
    private static final String CACHE_FIND_BY_ID = "OrderService.findById";
    private static final String CACHE_FIND_BY_USER = "OrderService.findByUser";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Cacheable(CACHE_FIND_ALL)
    public List<OrderResponse> findAllOrders() {
        log.info(this.getClass().getName() + " | " + "findAllOrders");
        return orderMapper.toListOrderResponse(orderRepository.findAll());
    }

    @Cacheable(CACHE_FIND_BY_ID)
    public OrderResponse findById(Long id) {
        log.info(this.getClass().getName() + " | " + "findById");
        return orderMapper.toOrderResponse(orderRepository.findById(id));
    }

    @Cacheable(CACHE_FIND_BY_USER)
    public List<OrderResponse> findByUser(String id) {
        log.info(this.getClass().getName() + " | " + "findByUser");
        User user = userService.findByUserId(id);
        return orderMapper.toListOrderResponse(orderRepository.findByUser(user));
    }

    @Transactional
    @CacheEvict(CACHE_FIND_ALL)
    public OrderResponse save(OrderRequest order) {
        UserResponse userResponse = userService.findById(UUID.fromString(order.getUser()));
        log.info(this.getClass().getName() + " | " + "save");
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

    @CachePut(CACHE_FIND_BY_ID)
    public OrderResponse updateState(Long id) {
        log.info(this.getClass().getName() + " | " + "updateState");
        Optional<Order> orderUpdate = orderRepository.findById(id);
        if (orderUpdate.isPresent() &&
                orderUpdate.get().getState().equals(OrderState.INCOMPLETE)) {
            orderUpdate.get().setState(OrderState.COMPLETE);
            return orderMapper.toOrderResponse(orderRepository.save(orderUpdate.get()));
        }
        return orderMapper.toOrderResponse(orderUpdate);
     }

}
