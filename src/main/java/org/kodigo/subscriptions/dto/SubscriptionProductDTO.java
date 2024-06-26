package org.kodigo.subscriptions.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionProductDTO {

    private Integer productId;

    private Integer quantity;

    private Double price;

    private Double discount;

}
