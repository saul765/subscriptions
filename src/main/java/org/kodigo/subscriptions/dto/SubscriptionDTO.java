package org.kodigo.subscriptions.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionDTO {

    private Integer id;

    private String description;

    private String startDate;

    private String endDate;

    private Integer customerId;

    private Integer merchantId;
}
