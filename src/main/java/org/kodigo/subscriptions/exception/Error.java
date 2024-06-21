package org.kodigo.subscriptions.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Error {

    private String responseCode;
    private String responseTitle;
    private String responseDescription;
    private List<Errors> errors;

    public Error(String responseCode, String responseDescription, List<Errors> errors) {
        super();
        this.responseCode = responseCode;
        this.responseDescription = responseDescription;
        this.errors = errors;
    }

    public Error(String responseCode, String responseDescription) {
        super();
        this.responseCode = responseCode;
        this.responseDescription = responseDescription;
    }

    public Error(String responseCode, String responseTitle, String responseDescription) {
        super();
        this.responseCode = responseCode;
        this.responseTitle = responseTitle;
        this.responseDescription = responseDescription;
    }

    public void addError(Errors error) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
    }

}
