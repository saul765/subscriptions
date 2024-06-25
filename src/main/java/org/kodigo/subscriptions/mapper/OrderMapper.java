package org.kodigo.subscriptions.mapper;


import org.kodigo.subscriptions.dto.OrderDTO;
import org.kodigo.subscriptions.entity.OrderEntity;
import org.kodigo.subscriptions.utils.interfaces.IDateService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Mapper
public abstract class OrderMapper {

    @Autowired
    protected IDateService dateService;

    private static final String ORDER_DATE_FORMAT = "dd-MM-yyyy";

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "subscriptionId", source = "subscription.id")
    @Mapping(target = "orderDate", source = "orderDate", qualifiedByName = "dateToString")
    public abstract OrderDTO entityToDto(OrderEntity orderEntity);


    @Mapping(target = "customer.id", source = "customerId")
    @Mapping(target = "id", source = "id")
    @Mapping(target = "subscription.id", source = "subscriptionId")
    @Mapping(target = "orderDate", source = "orderDate")
    public abstract OrderEntity dtoToEntity(OrderDTO orderDTO);

    @Named("dateToString")
    protected String dateToString(Date date) {
        return dateService.formatDate(date, ORDER_DATE_FORMAT);
    }
}
