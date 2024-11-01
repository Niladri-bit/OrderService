package com.assignment.order.service.OrderService.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.order.service.OrderService.entities.OrderEntity;


@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID>{

	List<OrderEntity> findByUserId(Long userId);
	Optional<OrderEntity> findByIdAndUserId(Long orderId, Long userId);
}
