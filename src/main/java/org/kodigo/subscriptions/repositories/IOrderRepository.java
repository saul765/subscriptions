package org.kodigo.subscriptions.repositories;

import org.kodigo.subscriptions.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByCustomerId(Long customerId);

    List<OrderEntity> findBySubscriptionId(Long subscriptionId);

}
