package org.kodigo.subscriptions.repositories;

import org.kodigo.subscriptions.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByCustomerId(Long customerId);

    List<OrderEntity> findBySubscriptionId(Long subscriptionId);

    List<OrderEntity> findByOrderDate(Date orderDate);

}
