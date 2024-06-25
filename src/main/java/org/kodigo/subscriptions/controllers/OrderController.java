package org.kodigo.subscriptions.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kodigo.subscriptions.enums.ResponseCodeEnum;
import org.kodigo.subscriptions.exception.AppException;
import org.kodigo.subscriptions.services.order.IOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final IOrderService orderService;

    @GetMapping("/search")
    public ResponseEntity<?> searchOrder(@RequestParam(value = "id", required = false) Integer id,
                                         @RequestParam(value = "subscriptionId", required = false) Integer subscriptionId,
                                         @RequestParam(value = "customerId", required = false) Integer customerId,
                                         @RequestParam(value = "orderDate", required = false) String orderDate) {
        log.info("[Controller]: Starting execution searchOrder");
        try {
            return Optional.ofNullable(id)
                    .map(orderService::getOrderById)
                    .map(ResponseEntity::ok)
                    .or(() -> Optional.ofNullable(subscriptionId)
                            .map(orderService::getOrderBySubscriptionId)
                            .map(ResponseEntity::ok))
                    .or(() -> Optional.ofNullable(customerId)
                            .map(orderService::getOrderByCustomerId)
                            .map(ResponseEntity::ok))
                    .or(() -> Optional.ofNullable(subscriptionId)
                            .map(orderService::getOrderBySubscriptionId)
                            .map(ResponseEntity::ok))
                    .orElseGet(() -> ResponseEntity.ok().body(orderService.getAllOrders()));
        } catch (
                Exception e) {
            if (e instanceof AppException) {
                throw e;
            } else {
                throw AppException.internalServerError(ResponseCodeEnum.CODE_500, e.getMessage());
            }
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") Integer orderId) {
        try {
            log.info("[Controller]: Starting execution deleteOrder");
            orderService.deleteOrder(orderId);
            log.info("[Controller]: Finishing execution deleteOrder");
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
