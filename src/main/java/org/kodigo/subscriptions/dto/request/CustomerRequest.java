package org.kodigo.subscriptions.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.kodigo.subscriptions.dto.CustomerDTO;

@Getter
@Setter
public class CustomerRequest {

    private CustomerDTO customerInformation;
}
