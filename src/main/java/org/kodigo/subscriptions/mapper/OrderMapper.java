package org.kodigo.subscriptions.mapper;


import org.kodigo.subscriptions.dto.OrderDTO;
import org.kodigo.subscriptions.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface OrderMapper {

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "subscriptionId", source = "subscription.id")
    @Mapping(target = "orderDate", source = "orderDate")
    OrderDTO entityToDto(OrderEntity orderEntity);


    @Mapping(target = "customer.id", source = "customerId")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "subscription.id", source = "subscriptionId")
    @Mapping(target = "orderDate", source = "orderDate")
    OrderEntity dtoToEntity(OrderDTO orderDTO);


}
