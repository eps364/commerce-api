package br.com.commerce.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.commerce.api.dto.order.OrderRequest;
import br.com.commerce.api.dto.order.OrderResponse;
import br.com.commerce.api.services.OrderService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(value = { "orders" })
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("invalidCache")
    public ResponseEntity<List<OrderResponse>> invalidCache() {
        log.info(this.getClass().getName() + " | " + "invalidCache");
        return ResponseEntity.ok(orderService.invalidCache());
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        log.info(this.getClass().getName() + " | " + "findAll");
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAllOrders());
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable Long id) {
        log.info(this.getClass().getName() + " | " + "findById: " + id);
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findById(id));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest order) {
        log.info(this.getClass().getName() + " | " + "create: " + order.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(order));
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<OrderResponse> updateState(@PathVariable Long id) {
        log.info(this.getClass().getName() + " | " + "updateState: " + id);
        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateState(id));
    }

}
