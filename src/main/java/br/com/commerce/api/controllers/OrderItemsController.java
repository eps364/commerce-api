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

import br.com.commerce.api.dto.order.orderitem.OrderItemRequest;
import br.com.commerce.api.dto.order.orderitem.OrderItemRequestPut;
import br.com.commerce.api.dto.order.orderitem.OrderItemResponse;
import br.com.commerce.api.services.OrderItemsService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = { "items" })
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

    @PutMapping("{id}")
    public ResponseEntity<OrderItemResponse> update(
            @RequestBody @Valid OrderItemRequestPut item,
            @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateOrderItems(item, id));
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderItemResponse> getById(@PathVariable Long id) {
        OrderItemResponse orderItemResponse = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(orderItemResponse);
    }

    @GetMapping
    public ResponseEntity<List<OrderItemResponse>> getAll() {
         return ResponseEntity.status(HttpStatus.OK).body(service.findAllOrderItems());
    }

    @GetMapping("order/{orderId}")
    public ResponseEntity<List<OrderItemResponse>> getByOrderId(@PathVariable Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAllOrderItemsByOrderId(orderId));
    }

}
