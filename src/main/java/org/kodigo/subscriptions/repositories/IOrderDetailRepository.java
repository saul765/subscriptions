package org.kodigo.subscriptions.repositories;

import org.kodigo.subscriptions.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {
}
