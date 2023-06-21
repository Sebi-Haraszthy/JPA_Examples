package com.springdata.JPAExamples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
public class OrderController {
    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/orders/{id}")
    public Order findById(@PathVariable Long id) {
        try {
            return orderService.findById(id);
        } catch (OrderNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found!", e);
        }
    }

    @GetMapping("/orders/localdatebetween")
    public List<Order> findByLocalDateBetween(@RequestParam LocalDate after, @RequestParam LocalDate before) {
        return orderService.findOrdersByLocalDateBetween(after, before);
    }

    @PostMapping("/orders")
    public Order saveOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @PutMapping("/orders/{id}")
    public Order updateOrder(@RequestBody Order updateOrder, @PathVariable Long id) {
        try {
            return orderService.updateOrder(updateOrder, id);
        } catch (OrderNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found!", e);
        }
    }

    @DeleteMapping("/orders/{id}")
    public void deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
        } catch (OrderNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found!", e);
        }
    }
}