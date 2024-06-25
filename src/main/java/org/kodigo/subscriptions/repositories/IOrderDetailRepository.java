package org.kodigo.subscriptions.repositories;

import org.kodigo.subscriptions.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderDetailRepository extends JpaRepository<OrderDetailEntity, Long> {

    List<OrderDetailEntity> findByOrderId(Long orderId);

    List<OrderDetailEntity> findByProductId(Long productId);


}
