package org.kodigo.subscriptions.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchantDTO {

    private Integer id;

    private String name;

    private String email;

    private String phoneNumber;

    private String address;
}
