package org.kodigo.subscriptions.services.customer;

import org.kodigo.subscriptions.dto.CustomerDTO;
import org.kodigo.subscriptions.entity.CustomerEntity;

import java.util.List;

public interface ICustomerService {

    List<CustomerDTO> getAllCustomers();

    List<CustomerDTO> getCustomersByName(String name);

    List<CustomerDTO> getCustomerByEmail(String email);

    List<CustomerDTO> getCustomerById(Integer customerId);

    CustomerEntity getCustomerEntityById(Integer customerId);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(Integer customerId, CustomerDTO customerDTO);

    void deleteCustomer(Integer customerId);
}
