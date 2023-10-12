package br.com.commerce.api.scheduled;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.commerce.api.models.Order;
import br.com.commerce.api.models.OrderState;
import br.com.commerce.api.repositories.OrderRepository;
import br.com.commerce.api.services.OrderService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@EnableScheduling
public class OrderScheduled {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Scheduled(cron = "${spring.cron.expression}", zone = "${spring.cron.timezone}")
    public void scheduled() {
        log.info(this.getClass().getName() + " | " + "scheduled order INCOMPLETE to ABANDONED");
        List<Order> listOrders = orderRepository.findByState(OrderState.INCOMPLETE);
        Instant cutAbandoned = Instant.now().minus(48, ChronoUnit.HOURS);
        for (Order order : listOrders) {
            if(order.getDateUpdate().isBefore(cutAbandoned)){
                orderService.abandonedOrder(order.getId());
            }
        }
    }

}
