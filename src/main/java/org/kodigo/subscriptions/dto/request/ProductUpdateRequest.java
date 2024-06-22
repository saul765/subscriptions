package org.kodigo.subscriptions.dto.request;


import lombok.Getter;
import lombok.Setter;
import org.kodigo.subscriptions.dto.ProductUpdateDTO;

@Setter
@Getter
public class ProductUpdateRequest {

    private ProductUpdateDTO productInformation;
}
