package org.kodigo.subscriptions.services.product;

import org.kodigo.subscriptions.dto.ProductDTO;
import org.kodigo.subscriptions.dto.ProductUpdateDTO;
import org.kodigo.subscriptions.entity.ProductEntity;

import java.util.List;

public interface IProductService {

    List<ProductDTO> getAllProducts();

    List<ProductDTO> getProductById(Integer productId);

    List<ProductDTO> getProductByName(String name);

    List<ProductDTO> getProductsByMerchantId(Integer merchantId);

    List<ProductDTO> getProductsByMerchantName(String merchantName);

    ProductEntity getProductEntityById(Integer productId);

    ProductDTO createProduct(ProductDTO productDTO);

    ProductDTO updateProduct(Integer productId, ProductUpdateDTO productDTO);

    void deleteProduct(Integer productId);
}
