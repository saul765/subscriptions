package org.kodigo.subscriptions.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MerchantDTO {

    private Integer id;

    private String name;

    private String email;

    private String phoneNumber;

    private String address;
}
