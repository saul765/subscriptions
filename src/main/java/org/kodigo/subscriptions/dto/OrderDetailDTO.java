package org.kodigo.subscriptions.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDTO {

    private Integer id;

    private Integer productId;

    private Double discount;

    private Integer quantity;

    private Double subtotal;

    private Double total;

}
