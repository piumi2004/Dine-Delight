package org.example.dinedelightsystems.repository;

import org.example.dinedelightsystems.model.Order;
import org.example.dinedelightsystems.model.OrderStatus;
import org.example.dinedelightsystems.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserOrderByCreatedAtDesc(User user);
    List<Order> findAllByUserAndStatus(User user, OrderStatus status);
}



