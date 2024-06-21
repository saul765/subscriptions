package org.kodigo.subscriptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ResponseCodeEnum {

    CODE_500("500", "Internal Server Error"),
    CODE_404("404", "Not Found"),
    CODE_400("400", "Bad Request");


    public final String code;
    public final String message;

    public static ResponseCodeEnum getResponseCode(String code) {
        return Arrays.stream(values())
                .filter(responseCode -> responseCode.code.equals(code))
                .findFirst()
                .orElseThrow();
    }
}
