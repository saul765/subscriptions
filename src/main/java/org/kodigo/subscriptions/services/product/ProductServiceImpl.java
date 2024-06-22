package org.kodigo.subscriptions.services.product;


import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kodigo.subscriptions.dto.ProductDTO;
import org.kodigo.subscriptions.dto.ProductUpdateDTO;
import org.kodigo.subscriptions.enums.ResponseCodeEnum;
import org.kodigo.subscriptions.exception.AppException;
import org.kodigo.subscriptions.mapper.ProductMapper;
import org.kodigo.subscriptions.repositories.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public List<ProductDTO> getAllProducts() {
        log.info("[Service]: Starting execution getAllProducts");
        List<ProductDTO> response = productRepository.findAll()
                .stream()
                .map(productMapper::toProductDTO)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Products not found");
        }

        log.info("[Service]: Finishing execution getAllProducts");
        return response;
    }

    @Override
    public List<ProductDTO> getProductById(Integer productId) {
        log.info("[Service]: Starting execution getProductById");
        List<ProductDTO> response = productRepository.findById(productId.longValue())
                .stream()
                .map(productMapper::toProductDTO)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Product not found");
        }

        log.info("[Service]: Finishing execution getProductById");
        return response;
    }

    @Override
    public List<ProductDTO> getProductByName(String name) {
        log.info("[Service]: Starting execution getProductByName");
        List<ProductDTO> response = productRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(productMapper::toProductDTO)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Product not found");
        }

        log.info("[Service]: Finishing execution getProductByName");
        return response;
    }

    @Override
    public List<ProductDTO> getProductsByMerchantId(Integer merchantId) {
        log.info("[Service]: Starting execution getProductsByMerchantId");
        List<ProductDTO> response = productRepository.findByMerchantId(merchantId.longValue())
                .stream()
                .map(productMapper::toProductDTO)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Products not found");
        }

        log.info("[Service]: Finishing execution getProductsByMerchantId");
        return response;
    }

    @Override
    public List<ProductDTO> getProductsByMerchantName(String merchantName) {
        log.info("[Service]: Starting execution getProductsByMerchantName");
        List<ProductDTO> response = productRepository.findByMerchantNameContainingIgnoreCase(merchantName)
                .stream()
                .map(productMapper::toProductDTO)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Products not found");
        }

        log.info("[Service]: Finishing execution getProductsByMerchantName");
        return response;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        log.info("[Service]: Starting execution createProduct");

        ProductDTO response = Optional.ofNullable(productDTO)
                .map(productMapper::toProduct)
                .map(productRepository::save)
                .map(productMapper::toProductDTO)
                .orElseThrow(
                        () -> AppException.internalServerError(ResponseCodeEnum.CODE_500, "Error creating product")
                );
        log.info("[Service]: Finishing execution createProduct");
        return response;
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(Integer productId, ProductUpdateDTO productDTO) {

        log.info("[Service]: Starting execution updateProduct");
        ProductDTO response = productRepository.findById(productId.longValue())
                .map(product -> {
                    Optional.ofNullable(productDTO.getName()).ifPresent(product::setName);
                    Optional.ofNullable(productDTO.getDescription()).ifPresent(product::setDescription);
                    Optional.ofNullable(productDTO.getPrice()).ifPresent(product::setPrice);
                    return product;
                })
                .map(productRepository::save)
                .map(productMapper::toProductDTO)
                .orElseThrow(() -> AppException.notFound(ResponseCodeEnum.CODE_404, "Product not found"));

        log.info("[Service]: Finishing execution updateProduct");
        return response;
    }

    @Override
    public void deleteProduct(Integer productId) {
        log.info("[Service]: Starting execution deleteProduct");
        productRepository.findById(productId.longValue())
                .map(product -> {
                    productRepository.deleteById(product.getId());
                    return product;
                })
                .orElseThrow(() -> AppException.notFound(ResponseCodeEnum.CODE_404, "Product not found"));
        log.info("[Service]: Finishing execution deleteProduct");
    }
}
