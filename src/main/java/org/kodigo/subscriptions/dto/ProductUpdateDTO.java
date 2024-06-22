package org.kodigo.subscriptions.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductUpdateDTO {

    private String name;

    private Double price;

    private String description;
}
