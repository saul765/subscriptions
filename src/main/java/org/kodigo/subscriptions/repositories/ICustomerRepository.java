package org.kodigo.subscriptions.repositories;

import org.kodigo.subscriptions.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerEntity, Long> {


    List<CustomerEntity> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName);

    List<CustomerEntity> findByEmail(String email);

}
