package org.kodigo.subscriptions.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String address;
}
