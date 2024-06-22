package org.kodigo.subscriptions.controllers;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.kodigo.subscriptions.dto.ProductDTO;
import org.kodigo.subscriptions.dto.request.ProductCreateRequest;
import org.kodigo.subscriptions.dto.request.ProductUpdateRequest;
import org.kodigo.subscriptions.enums.ResponseCodeEnum;
import org.kodigo.subscriptions.exception.AppException;
import org.kodigo.subscriptions.services.product.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final IProductService productService;

    @GetMapping("/search")
    public ResponseEntity<?> searchProduct(@RequestParam(value = "id", required = false) Integer id,
                                           @RequestParam(value = "name", required = false) String name,
                                           @RequestParam(value = "merchantId", required = false) Integer merchantId,
                                           @RequestParam(value = "merchantName", required = false) String merchantName) {
        log.info("[Controller]: Starting execution searchProduct");

        try {
            return Optional.ofNullable(id)
                    .map(productService::getProductById)
                    .map(ResponseEntity::ok)
                    .or(() -> Optional.ofNullable(name)
                            .map(productService::getProductByName)
                            .map(ResponseEntity::ok))
                    .or(() -> Optional.ofNullable(merchantId)
                            .map(productService::getProductsByMerchantId)
                            .map(ResponseEntity::ok))
                    .or(() -> Optional.ofNullable(merchantName)
                            .map(productService::getProductsByMerchantName)
                            .map(ResponseEntity::ok))
                    .orElseGet(() -> ResponseEntity.ok().body(productService.getAllProducts()));
        } catch (Exception e) {
            if (e instanceof AppException) {
                throw e;
            } else {
                throw AppException.internalServerError(ResponseCodeEnum.CODE_500, e.getMessage());
            }
        }
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductCreateRequest request) {
        try {
            log.info("[Controller]: Starting execution createProduct");
            val response = productService.createProduct(request.getProductInformation());
            log.info("[Controller]: Finishing execution createProduct");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            if (e instanceof AppException) {
                throw e;
            } else {
                throw AppException.internalServerError(ResponseCodeEnum.CODE_500, e.getMessage());
            }
        }
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("productId") Integer productId, @RequestBody ProductUpdateRequest request) {
        try {
            log.info("[Controller]: Starting execution updateProduct");
            val response = productService.updateProduct(productId, request.getProductInformation());
            log.info("[Controller]: Finishing execution updateProduct");
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            if (e instanceof AppException) {
                throw e;
            } else {
                throw AppException.internalServerError(ResponseCodeEnum.CODE_500, e.getMessage());
            }
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Integer productId) {
        try {
            log.info("[Controller]: Starting execution deleteProduct");
            productService.deleteProduct(productId);
            log.info("[Controller]: Finishing execution deleteProduct");
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
