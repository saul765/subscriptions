package org.kodigo.subscriptions.dto.request;


import lombok.Getter;
import lombok.Setter;
import org.kodigo.subscriptions.dto.ProductDTO;

@Getter
@Setter
public class ProductCreateRequest {

    private ProductDTO productInformation;

}
