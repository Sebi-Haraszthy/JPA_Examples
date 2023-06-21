package com.springdata.JPAExamples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {
    private OrderRepository orderRepository;

    @Autowired
    public Runner(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) {
        Order order1 = new Order("type1", LocalDate.of(2023, 6, 2), OrderStatus.PENDING);
        Order order2 = new Order("type3", LocalDate.of(2022, 5, 19), OrderStatus.SHIPPED);
        Order order3 = new Order("type2", LocalDate.of(2023, 8, 7), OrderStatus.RECEIVED);
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);

        Order foundOrder = orderRepository.findById(2L).orElse(new Order());

        System.out.println("Found order is: " + foundOrder);

        order2.setOrderStatus(OrderStatus.RECEIVED);
        orderRepository.save(order2);

        System.out.println("Pending orders: " + orderRepository.findAllOrdersByOrderStatus(OrderStatus.PENDING));
        System.out.println("All orders are: " + orderRepository.findAll());
        System.out.println("Top 3 orders by product type are: " + orderRepository.findTop3ByOrderByProductType());
        System.out.println("Received orders: " + orderRepository.findAllByOrderStatusIn(List.of(OrderStatus.RECEIVED)));
    }
}