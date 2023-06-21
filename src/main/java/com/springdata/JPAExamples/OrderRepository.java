package com.springdata.JPAExamples;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatusList);

    List<Order> findOrdersByLocalDateBetween(LocalDate after, LocalDate before);

    List<Order> findOrderByProductTypeContainsAndLocalDateIsAfter(String productType, LocalDate after);

    List<Order> findTop3ByOrderByProductType();

    Order findOrderByProductType(String productType);

    @Query(value = "SELECT o FROM Order o WHERE o.orderStatus = :orderStatus", nativeQuery = false)
    List<Order> findAllOrdersByOrderStatus(@Param("orderStatus") OrderStatus orderStatus);
}