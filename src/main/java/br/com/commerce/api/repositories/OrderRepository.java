package br.com.commerce.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.commerce.api.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
