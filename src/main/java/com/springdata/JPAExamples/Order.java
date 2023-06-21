package com.springdata.JPAExamples;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "product_type")
    private String productType;
    @Column(name = "local_date")
    private LocalDate localDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;
    @Transient
    private String orderProgress;

    public Order(String productType, LocalDate localDate, OrderStatus orderStatus) {
        this.productType = productType;
        this.localDate = localDate;
        this.orderStatus = orderStatus;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order: " + "id = " + id + "; productType = " + productType + "; localDate = " + localDate + "; orderStatus = " + orderStatus + ".";
    }
}