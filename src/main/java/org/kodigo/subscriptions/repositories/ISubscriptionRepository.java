package org.kodigo.subscriptions.repositories;

import org.kodigo.subscriptions.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ISubscriptionRepository extends JpaRepository<SubscriptionEntity, Long> {

    List<SubscriptionEntity> findByCustomerId(Long customerId);

    List<SubscriptionEntity> findByMerchantId(Long merchantId);

    List<SubscriptionEntity> findByStartDate(Date startDate);

    List<SubscriptionEntity> findByEndDate(Date endDate);

}
