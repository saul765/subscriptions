package org.kodigo.subscriptions.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.kodigo.subscriptions.dto.CustomerDTO;
import org.kodigo.subscriptions.dto.request.CustomerRequest;
import org.kodigo.subscriptions.enums.ResponseCodeEnum;
import org.kodigo.subscriptions.exception.AppException;
import org.kodigo.subscriptions.services.customer.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final ICustomerService customerService;

    @GetMapping("/search")
    public ResponseEntity<?> searchCustomer(@RequestParam(value = "id", required = false) Integer id,
                                            @RequestParam(value = "email", required = false) String email,
                                            @RequestParam(value = "name", required = false) String name) {
        log.info("[Controller]: Starting execution searchCustomer");

        try {
            return Optional.ofNullable(id)
                    .map(customerService::getCustomerById)
                    .map(ResponseEntity::ok)
                    .or(() -> Optional.ofNullable(email)
                            .map(customerService::getCustomerByEmail)
                            .map(ResponseEntity::ok))
                    .or(() -> Optional.ofNullable(name)
                            .map(customerService::getCustomersByName)
                            .map(ResponseEntity::ok))
                    .orElseGet(() -> ResponseEntity.ok().body(customerService.getAllCustomers()));
        } catch (Exception e) {
            if (e instanceof AppException) {
                throw e;
            } else {
                throw AppException.internalServerError(ResponseCodeEnum.CODE_500, e.getMessage());
            }
        }
    }

    @PostMapping()
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerRequest request) {
        try {
            log.info("[Controller]: Starting execution createCustomer");
            val response = customerService.createCustomer(request.getCustomerInformation());
            log.info("[Controller]: Finishing execution createCustomer");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            if (e instanceof AppException) {
                throw e;
            } else {
                throw AppException.internalServerError(ResponseCodeEnum.CODE_500, e.getMessage());
            }
        }
    }

    @PutMapping("{customerId}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable("customerId") Integer customerId, @RequestBody CustomerRequest request) {
        try {
            log.info("[Controller]: Starting execution updateCustomer");
            val response = customerService.updateCustomer(customerId, request.getCustomerInformation());
            log.info("[Controller]: Finishing execution updateCustomer");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            if (e instanceof AppException) {
                throw e;
            } else {
                throw AppException.internalServerError(ResponseCodeEnum.CODE_500, e.getMessage());
            }
        }
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") Integer customerId) {
        try {
            log.info("[Controller]: Starting execution deleteCustomer");
            customerService.deleteCustomer(customerId);
            log.info("[Controller]: Finishing execution deleteCustomer");
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            if (e instanceof AppException) {
                throw e;
            } else {
                throw AppException.internalServerError(ResponseCodeEnum.CODE_500, e.getMessage());
            }
        }
    }
}