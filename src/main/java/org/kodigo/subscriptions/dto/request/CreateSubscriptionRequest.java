package org.kodigo.subscriptions.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.kodigo.subscriptions.dto.SubscriptionDTO;
import org.kodigo.subscriptions.dto.SubscriptionProductDTO;

import java.util.List;

@Getter
@Setter
public class CreateSubscriptionRequest {

    private SubscriptionDTO subscription;

    private List<SubscriptionProductDTO> subscriptionProducts;

}
