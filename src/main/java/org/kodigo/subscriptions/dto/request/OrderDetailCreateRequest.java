package org.kodigo.subscriptions.dto.request;


import lombok.Getter;
import lombok.Setter;
import org.kodigo.subscriptions.dto.OrderDetailDTO;

@Getter
@Setter
public class OrderDetailCreateRequest {

    private OrderDetailDTO orderDetailInformation;
}
