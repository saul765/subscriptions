package org.kodigo.subscriptions.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderDTO {

    private Long id;

    private Long customerId;

    private Long subscriptionId;

    private Date orderDate;
}
