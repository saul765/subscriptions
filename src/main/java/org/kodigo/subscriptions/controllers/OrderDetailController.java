package org.kodigo.subscriptions.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.kodigo.subscriptions.dto.OrderDetailDTO;
import org.kodigo.subscriptions.dto.request.OrderDetailCreateRequest;
import org.kodigo.subscriptions.dto.request.OrderDetailUpdateRequest;
import org.kodigo.subscriptions.enums.ResponseCodeEnum;
import org.kodigo.subscriptions.exception.AppException;
import org.kodigo.subscriptions.services.order.IOrderDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/order-details")
public class OrderDetailController {

    private final IOrderDetailService orderDetailService;

    @GetMapping("/search")
    public ResponseEntity<?> searchOrderDetail(@RequestParam(value = "id", required = false) Integer id,
                                               @RequestParam(value = "orderId", required = false) Integer orderId,
                                               @RequestParam(value = "productId", required = false) Integer productId) {
        log.info("[Controller]: Starting execution searchOrderDetail");

        try {
            return Optional.ofNullable(id)
                    .map(orderDetailService::getOrderDetailById)
                    .map(ResponseEntity::ok)
                    .or(() -> Optional.ofNullable(orderId)
                            .map(orderDetailService::getOrderDetailByOrderId)
                            .map(ResponseEntity::ok))
                    .or(() -> Optional.ofNullable(productId)
                            .map(orderDetailService::getOrderDetailByProductId)
                            .map(ResponseEntity::ok))
                    .orElseGet(() -> ResponseEntity.ok().body(orderDetailService.getAllOrderDetails()));
        } catch (Exception e) {
            if (e instanceof AppException) {
                throw e;
            } else {
                throw AppException.internalServerError(ResponseCodeEnum.CODE_500, e.getMessage());
            }
        }
    }

    @PutMapping("/{orderDetailId}")
    public ResponseEntity<OrderDetailDTO> updateOrderDetail(@PathVariable("orderDetailId") Integer orderDetailId, @RequestBody OrderDetailUpdateRequest request) {
        try {
            log.info("[Controller]: Starting execution updateOrderDetail");
            val response = orderDetailService.updateOrderDetail(orderDetailId, request.getOrderDetailInformation());
            log.info("[Controller]: Finishing execution updateOrderDetail");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            if (e instanceof AppException) {
                throw e;
            } else {
                throw AppException.internalServerError(ResponseCodeEnum.CODE_500, e.getMessage());
            }
        }
    }

    @DeleteMapping("/{orderDetailId}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable("orderDetailId") Integer orderDetailId) {
        try {
            log.info("[Controller]: Starting execution deleteOrderDetail");
            orderDetailService.deleteOrderDetail(orderDetailId);
            log.info("[Controller]: Finishing execution deleteOrderDetail");
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
