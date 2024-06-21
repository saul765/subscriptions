package org.kodigo.subscriptions.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GeneralResponseDTO {

    private List<Error> generalResponse;

    public void addGeneralError(Error error) {
        if (this.generalResponse == null) {
            this.generalResponse = new ArrayList<>();
        }
        this.generalResponse.add(error);
    }
}
