package org.kodigo.subscriptions.controllers;


import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.kodigo.subscriptions.dto.SubscriptionDTO;
import org.kodigo.subscriptions.dto.request.CreateSubscriptionRequest;
import org.kodigo.subscriptions.enums.ResponseCodeEnum;
import org.kodigo.subscriptions.exception.AppException;
import org.kodigo.subscriptions.services.subscriptions.ISubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/subscriptions")
public class SubscriptionController {

    private final ISubscriptionService subscriptionService;


    @GetMapping("/search")
    public ResponseEntity<?> searchSubscription(@RequestParam(value = "id", required = false) Integer id,
                                                @RequestParam(value = "merchantId", required = false) Integer merchantId,
                                                @RequestParam(value = "customerId", required = false) Integer customerId,
                                                @RequestParam(value = "startDate", required = false) String startDate,
                                                @RequestParam(value = "endDate", required = false) String endDate) {
        log.info("[Controller]: Starting execution searchOrder");

        try {

            return Optional.ofNullable(id)
                    .map(subscriptionService::getSubscriptionsById)
                    .map(ResponseEntity::ok)
                    .or(() -> Optional.ofNullable(merchantId)
                            .map(subscriptionService::getSubscriptionsByCustomerId)
                            .map(ResponseEntity::ok))
                    .or(() -> Optional.ofNullable(customerId)
                            .map(subscriptionService::getSubscriptionsByCustomerId)
                            .map(ResponseEntity::ok))
                    .or(() -> Optional.ofNullable(startDate)
                            .map(this::getSubscriptionsByStartDateWrapper)
                            .map(ResponseEntity::ok))
                    .or(() -> Optional.ofNullable(endDate)
                            .map(this::getSubscriptionsByEndDateWrapper)
                            .map(ResponseEntity::ok))
                    .orElseGet(() -> ResponseEntity.ok().body(subscriptionService.getAllSubscriptions()));

        } catch (Exception e) {
            if (e instanceof AppException) {
                throw e;
            } else {
                throw AppException.internalServerError(ResponseCodeEnum.CODE_500, e.getMessage());
            }
        }
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<SubscriptionDTO> createSubscription(@RequestBody CreateSubscriptionRequest request) {
        try {
            log.info("[Controller]: Starting execution createSubscription");
            SubscriptionDTO response = subscriptionService.createSubscription(request);
            log.info("[Controller]: Finishing execution createSubscription");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            if (e instanceof AppException) {
                throw e;
            } else {
                throw AppException.internalServerError(ResponseCodeEnum.CODE_500, e.getMessage());
            }
        }
    }


    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable("subscriptionId") Integer subscriptionId) {
        try {
            log.info("[Controller]: Starting execution deleteSubscription");
            subscriptionService.deleteSubscription(subscriptionId);
            log.info("[Controller]: Finishing execution deleteSubscription");
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            if (e instanceof AppException) {
                throw e;
            } else {
                throw AppException.internalServerError(ResponseCodeEnum.CODE_500, e.getMessage());
            }
        }
    }

    @SneakyThrows
    private List<SubscriptionDTO> getSubscriptionsByStartDateWrapper(String startDate) {
        try {
            return subscriptionService.getSubscriptionsByStartDate(startDate);
        } catch (Exception e) {
            if (e instanceof AppException) {
                throw e;
            } else {
                throw AppException.internalServerError(ResponseCodeEnum.CODE_500, e.getMessage());
            }
        }
    }

    @SneakyThrows
    private List<SubscriptionDTO> getSubscriptionsByEndDateWrapper(String endDate) {
        try {
            return subscriptionService.getSubscriptionsByEndDate(endDate);
        } catch (Exception e) {
            if (e instanceof AppException) {
                throw e;
            } else {
                throw AppException.internalServerError(ResponseCodeEnum.CODE_500, e.getMessage());
            }
        }
    }

}
