package com.assignment.order.service.OrderService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.assignment.order.service.OrderService.DTO.CreateOrderDTO;
import com.assignment.order.service.OrderService.DTO.OrderResponseDTO;
import com.assignment.order.service.OrderService.service.OrderService;


@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(path="/orders/create")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestHeader("Authorization") String token,@RequestBody CreateOrderDTO createOrderDTO) {
    	OrderResponseDTO createdOrder = orderService.createOrder(createOrderDTO, token);
    	return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }
    
    @GetMapping(path="/orders")
    public ResponseEntity<List<OrderResponseDTO>> getMyOrders(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(orderService.getAllOrders(token));
    }
    
    @GetMapping(path="/orders/{orderId}")
    public ResponseEntity<OrderResponseDTO> getMyOrder(@RequestHeader("Authorization") String token,@PathVariable("orderId") Long orderId) {
    	return ResponseEntity.ok(orderService.getOrderById(orderId,token));
    }
    
    @DeleteMapping(path="/orders/{orderId}")
    public ResponseEntity<String> cancelMyOrder(@RequestHeader("Authorization") String token,@PathVariable("orderId") Long orderId) {
    	orderService.cancelOrder(orderId, token);
    	return ResponseEntity.ok("Order canceled successfully.");
    }
}
