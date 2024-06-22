package org.kodigo.subscriptions.repositories;

import org.kodigo.subscriptions.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByNameContainingIgnoreCase(String name);

    List<ProductEntity> findByMerchantId(Long merchantId);

    List<ProductEntity> findByMerchantNameContainingIgnoreCase(String merchantName);

}
