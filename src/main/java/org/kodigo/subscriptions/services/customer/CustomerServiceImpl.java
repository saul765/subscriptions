package org.kodigo.subscriptions.services.customer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kodigo.subscriptions.dto.CustomerDTO;
import org.kodigo.subscriptions.enums.ResponseCodeEnum;
import org.kodigo.subscriptions.exception.AppException;
import org.kodigo.subscriptions.mapper.CustomerMapper;
import org.kodigo.subscriptions.repositories.ICustomerRepository;
import org.kodigo.subscriptions.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        log.info("[Service]: Starting execution getAllCustomers");
        List<CustomerDTO> response = customerRepository.findAll().stream()
                .map(customerMapper::entityToDto)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Customers not found");
        }

        log.info("[Service]: Finishing execution getAllCustomers");
        return response;
    }

    @Override
    public List<CustomerDTO> getCustomersByName(String name) {
        log.info("[Service]: Starting execution getCustomersByName");

        String nameSplit[] = name.split(" ");

        String firstName = nameSplit[0];

        String lastName = nameSplit.length > 1 ? nameSplit[1] : Constants.EMPTY_CHARACTER;

        List<CustomerDTO> response = customerRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName)
                .stream()
                .map(customerMapper::entityToDto)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Customers not found");
        }

        log.info("[Service]: Finishing execution getCustomersByName");
        return response;
    }

    @Override
    public List<CustomerDTO> getCustomerByEmail(String email) {
        log.info("[Service]: Starting execution getCustomerByEmail");
        List<CustomerDTO> response = customerRepository.findByEmail(email)
                .stream()
                .map(customerMapper::entityToDto)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Customers not found");
        }

        log.info("[Service]: Finishing execution getCustomerByEmail");
        return response;
    }

    @Override
    public List<CustomerDTO> getCustomerById(Integer customerId) {
        log.info("[Service]: Starting execution getCustomerById");
        List<CustomerDTO> response = customerRepository.findById(customerId.longValue()).stream()
                .map(customerMapper::entityToDto)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Customers not found");
        }

        log.info("[Service]: Finishing execution getCustomerById");
        return response;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        log.info("[Service]: Starting execution createCustomer");
        CustomerDTO response = Optional.ofNullable(customerDTO)
                .map(customerMapper::dtoToEntity)
                .map(customerRepository::save)
                .map(customerMapper::entityToDto)
                .orElseThrow(() -> AppException.internalServerError(ResponseCodeEnum.CODE_500, "Error creating customer"));

        log.info("[Service]: Finishing execution createCustomer");
        return response;
    }

    @Override
    public CustomerDTO updateCustomer(Integer customerId, CustomerDTO customerDTO) {
        log.info("[Service]: Starting execution updateCustomer");
        CustomerDTO response = customerRepository.findById(customerId.longValue())
                .map(customer -> {
                    Optional.ofNullable(customerDTO.getFirstName()).ifPresent(customer::setFirstName);
                    Optional.ofNullable(customerDTO.getLastName()).ifPresent(customer::setLastName);
                    Optional.ofNullable(customerDTO.getEmail()).ifPresent(customer::setEmail);
                    Optional.ofNullable(customerDTO.getPhoneNumber()).ifPresent(customer::setPhoneNumber);
                    Optional.ofNullable(customerDTO.getAddress()).ifPresent(customer::setAddress);
                    return customer;
                })
                .map(customerRepository::save)
                .map(customerMapper::entityToDto)
                .orElseThrow(() -> AppException.notFound(ResponseCodeEnum.CODE_404, "Customer not found"));

        log.info("[Service]: Finishing execution updateCustomer");
        return response;
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        log.info("[Service]: Starting execution deleteCustomer");
        customerRepository.findById(customerId.longValue())
                .map(customer -> {
                    customerRepository.deleteById(customer.getId());
                    return customer;
                })
                .orElseThrow(() -> AppException.notFound(ResponseCodeEnum.CODE_404, "Customer not found"));
        log.info("[Service]: Finishing execution deleteCustomer");
    }
}