package org.kodigo.subscriptions.mapper;

import org.kodigo.subscriptions.dto.OrderDetailDTO;
import org.kodigo.subscriptions.entity.OrderDetailEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderDetailMapper {


    @Mapping(target = "id", source = "id")
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "discount", source = "discount")
    OrderDetailDTO entityToDto(OrderDetailEntity orderDetailEntity);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "product.id", source = "productId")
    @Mapping(target = "order.id", source = "orderId")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "discount", source = "discount")
    OrderDetailEntity dtoToEntity(OrderDetailDTO orderDetailDTO);


}
