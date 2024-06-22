package org.kodigo.subscriptions.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "merchants")
public class MerchantEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "merchant_id")
    private Long id;

    @Column(name = "merchant_name")
    private String name;

    @Column(name = "contact_email")
    private String email;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "address")
    private String address;


}
