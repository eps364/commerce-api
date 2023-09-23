package br.com.commerce.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.commerce.api.dto.order.OrderItemRequest;
import br.com.commerce.api.dto.order.OrderItemResponse;
import br.com.commerce.api.services.OrderItemsService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = { "/order/items" })
public class OrderItemsController {

    @Autowired
    private OrderItemsService service;

    @PostMapping
    public ResponseEntity<OrderItemResponse> add(@RequestBody @Valid OrderItemRequest item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(item));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> remove(@PathVariable Long id) {
        service.deleteOrderItems(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity<OrderItemResponse> update(
            @RequestBody @Valid OrderItemRequest item,
            @PathVariable Long id) {
        service.updateOrderItems(item, id);
        return null;
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderItemResponse> getById(Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderItemResponse>> getAll() {
         return ResponseEntity.ok().body(service.findAllOrderItems());
    }

    @GetMapping("{orderId}")
    public ResponseEntity<List<OrderItemResponse>> getByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.ok().body(service.findAllOrderItemsByOrderId(orderId));
    }

}
