package org.kodigo.subscriptions.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionCreateDTO {

    private String description;

    private String startDate;

    private String endDate;

    private Integer customerId;

    private Integer merchantId;

}
