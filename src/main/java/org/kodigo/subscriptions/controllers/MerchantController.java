package org.kodigo.subscriptions.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.kodigo.subscriptions.dto.MerchantDTO;
import org.kodigo.subscriptions.dto.request.MerchantRequest;
import org.kodigo.subscriptions.enums.ResponseCodeEnum;
import org.kodigo.subscriptions.exception.AppException;
import org.kodigo.subscriptions.services.merchant.IMerchantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/merchants")
public class MerchantController {

    private final IMerchantService merchantService;


    @GetMapping("/search")
    public ResponseEntity<?> searchMerchant(@RequestParam(value = "id", required = false) Integer id,
                                            @RequestParam(value = "email", required = false) String email,
                                            @RequestParam(value = "name", required = false) String name) {
        log.info("[Controller]: Starting execution searchMerchant");

        try {
            return Optional.ofNullable(id)
                    .map(merchantService::getMerchantById)
                    .map(ResponseEntity::ok)
                    .or(() -> Optional.ofNullable(email)
                            .map(merchantService::getMerchantByEmail)
                            .map(ResponseEntity::ok))
                    .or(() -> Optional.ofNullable(name)
                            .map(merchantService::getMerchantsByName)
                            .map(ResponseEntity::ok))
                    .orElseGet(() -> ResponseEntity.ok().body(merchantService.getAllMerchants()));
        } catch (Exception e) {
            if (e instanceof AppException) {
                throw e;
            } else {
                throw AppException.internalServerError(ResponseCodeEnum.CODE_500, e.getMessage());
            }
        }

    }

    @PostMapping()
    public ResponseEntity<MerchantDTO> createMerchant(@RequestBody MerchantRequest request) {
        try {
            log.info("[Controller]: Starting execution createMerchant");
            val response = merchantService.createMerchant(request.getMerchantInformation());
            log.info("[Controller]: Finishing execution createMerchant");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            if (e instanceof AppException) {
                throw e;
            } else {
                throw AppException.internalServerError(ResponseCodeEnum.CODE_500, e.getMessage());
            }
        }
    }

    @PutMapping("{merchantId}")
    public ResponseEntity<MerchantDTO> updateMerchant(@PathVariable("merchantId") Integer merchantId, @RequestBody MerchantRequest request) {
        try {
            log.info("[Controller]: Starting execution updateMerchant");
            val response = merchantService.updateMerchant(merchantId, request.getMerchantInformation());
            log.info("[Controller]: Finishing execution updateMerchant");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            if (e instanceof AppException) {
                throw e;
            } else {
                throw AppException.internalServerError(ResponseCodeEnum.CODE_500, e.getMessage());
            }
        }
    }

    @DeleteMapping("{merchantId}")
    public ResponseEntity<Void> deleteMerchant(@PathVariable("merchantId") Integer merchantId) {
        try {
            log.info("[Controller]: Starting execution deleteMerchant");
            merchantService.deleteMerchant(merchantId);
            log.info("[Controller]: Finishing execution deleteMerchant");
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
