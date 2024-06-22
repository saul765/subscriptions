package org.kodigo.subscriptions.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private Integer id;

    private String name;

    private Double price;

    private String description;

    private Integer merchantId;

}
