package org.kodigo.subscriptions.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.kodigo.subscriptions.enums.ResponseCodeEnum;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AppException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final GeneralResponseDTO error;
    private final HttpStatus status;

    public static AppException badRequest(ResponseCodeEnum responseCode, String responseDescription) {
        GeneralResponseDTO response = new GeneralResponseDTO();
        Error error = new Error(responseCode.code, responseDescription);
        response.addGeneralError(error);
        return new AppException(response, HttpStatus.BAD_REQUEST);
    }

    public static AppException notFound(ResponseCodeEnum responseCode, String responseDescription) {
        GeneralResponseDTO response = new GeneralResponseDTO();
        response.addGeneralError(new Error(responseCode.code, responseDescription));
        return new AppException(response, HttpStatus.NOT_FOUND);
    }

    public static AppException badRequest(String responseTitle, ResponseCodeEnum responseCode, String responseDescription) {
        GeneralResponseDTO response = new GeneralResponseDTO();
        response.addGeneralError(new Error(responseCode.code, responseTitle, responseDescription));
        return new AppException(response, HttpStatus.BAD_REQUEST);
    }

    public static AppException notFound(String responseTitle, ResponseCodeEnum responseCode, String responseDescription) {
        GeneralResponseDTO response = new GeneralResponseDTO();
        response.addGeneralError(new Error(responseCode.code, responseTitle, responseDescription));
        return new AppException(response, HttpStatus.NOT_FOUND);
    }

    public static AppException conflict(ResponseCodeEnum responseCode, String responseDescription) {
        GeneralResponseDTO response = new GeneralResponseDTO();
        Error error = new Error(responseCode.code, responseDescription);
        response.addGeneralError(error);
        return new AppException(response, HttpStatus.CONFLICT);
    }

    public static AppException internalServerError(ResponseCodeEnum responseCode, String responseDescription) {
        GeneralResponseDTO response = new GeneralResponseDTO();
        Error error = new Error(responseCode.code, responseDescription);
        response.addGeneralError(error);
        return new AppException(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static AppException internalServerError(ResponseCodeEnum responseCode, String responseDescription, Throwable cause, Logger logger) {
        logger.error(responseDescription, cause);
        return internalServerError(responseCode, responseDescription);
    }

    public static AppException genericError(ResponseCodeEnum responseCode, Integer statusCode, String responseDescription, List<Errors> errors) {
        GeneralResponseDTO response = new GeneralResponseDTO();
        Error error = new Error(responseCode.code, responseDescription);
        error.setErrors(errors);
        response.addGeneralError(error);
        return new AppException(response, HttpStatus.valueOf(statusCode));
    }


}
