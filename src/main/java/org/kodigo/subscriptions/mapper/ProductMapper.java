package org.kodigo.subscriptions.mapper;


import org.kodigo.subscriptions.dto.ProductDTO;
import org.kodigo.subscriptions.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ProductMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "merchant.id", target = "merchantId")
    ProductDTO toProductDTO(ProductEntity product);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "merchantId", target = "merchant.id")
    ProductEntity toProduct(ProductDTO productDTO);
}
