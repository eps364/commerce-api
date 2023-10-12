package br.com.commerce.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.commerce.api.models.Order;
import br.com.commerce.api.models.OrderState;
import br.com.commerce.api.models.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUser(User user);

    List<Order> findByState(OrderState state);
}
