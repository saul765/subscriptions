package org.kodigo.subscriptions.exception;

import lombok.extern.slf4j.Slf4j;
import org.kodigo.subscriptions.enums.ResponseCodeEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class ControllerAdvisor {
    private static final String ERROR_DEFAULT_MESSAGE = "An error occurred during the execution of the service";
    private static final String ERROR_FORMAT = "Error: {}";

    @ExceptionHandler(AppException.class)
    public ResponseEntity<GeneralResponseDTO> custom(AppException ex, WebRequest request) {
        log.error(ERROR_DEFAULT_MESSAGE, ex);
        log.info(ERROR_FORMAT, ex.getError());
        return ResponseEntity.status(ex.getStatus()).body(ex.getError());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public GeneralResponseDTO validationRequest(MethodArgumentNotValidException ex, WebRequest request) {
        log.error("Error: {}", ex.getFieldError());
        GeneralResponseDTO response = new GeneralResponseDTO();
        response.addGeneralError(new Error(ResponseCodeEnum.CODE_400.code, Objects.requireNonNull(ex.getFieldError()).getDefaultMessage()));
        return response;
    }
}
