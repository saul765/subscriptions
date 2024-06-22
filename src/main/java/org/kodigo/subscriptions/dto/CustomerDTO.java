package org.kodigo.subscriptions.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String address;
}
