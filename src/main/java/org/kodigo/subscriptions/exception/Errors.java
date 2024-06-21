package org.kodigo.subscriptions.exception;

import lombok.Data;

import java.io.Serializable;

@Data
public class Errors implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
    private String detail;
    private String id;
    private String title;

}
