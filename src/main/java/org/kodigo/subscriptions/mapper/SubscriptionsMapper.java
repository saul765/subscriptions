package org.kodigo.subscriptions.mapper;


import org.kodigo.subscriptions.dto.SubscriptionDTO;
import org.kodigo.subscriptions.entity.SubscriptionEntity;
import org.kodigo.subscriptions.utils.interfaces.IDateService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.Date;

@Mapper
public abstract class SubscriptionsMapper {

    @Autowired
    protected IDateService dateService;

    private static final String SUBSCRIPTION_DATE_FORMAT = "dd-MM-yyyy";

    @Mapping(target = "id", source = "id")
    @Mapping(target = "merchantId", source = "merchant.id")
    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "startDate", source = "startDate", qualifiedByName = "dateToString")
    @Mapping(target = "endDate", source = "endDate", qualifiedByName = "dateToString")
    public abstract SubscriptionDTO entityToDTO(SubscriptionEntity subscription);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "customer.id", source = "customerId")
    @Mapping(target = "merchant.id", source = "merchantId")
    @Mapping(target = "startDate", source = "startDate", qualifiedByName = "stringToDate")
    @Mapping(target = "endDate", source = "endDate", qualifiedByName = "stringToDate")
    @Mapping(target = "description", source = "description")
    public abstract SubscriptionEntity dtoToEntity(SubscriptionDTO subscriptionDTO);


    @Named("dateToString")
    protected String dateToString(Date date) {
        return dateService.formatDate(date, SUBSCRIPTION_DATE_FORMAT);
    }

    @Named("stringToDate")
    protected Date stringToDate(String date) throws ParseException {
        return dateService.parseToDate(date, SUBSCRIPTION_DATE_FORMAT);
    }

}
