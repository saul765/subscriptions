package org.kodigo.subscriptions.repositories;

import org.kodigo.subscriptions.entity.MerchantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMerchantRepository extends JpaRepository<MerchantEntity, Long> {


    List<MerchantEntity> findByNameContainingIgnoreCase(String name);

    List<MerchantEntity> findByEmail(String email);
}
