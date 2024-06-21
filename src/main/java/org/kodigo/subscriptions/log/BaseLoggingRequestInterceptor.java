package org.kodigo.subscriptions.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

@Slf4j
public abstract class BaseLoggingRequestInterceptor implements ClientHttpRequestInterceptor {
    @NonNull
    @Override
    public ClientHttpResponse intercept(
            @NonNull HttpRequest request,
            @NonNull byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        interceptRequest(request, body);
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponse(response);

        return response;
    }

    protected void interceptRequest(HttpRequest request, byte[] body) {
    }

    protected void logRequest(HttpRequest request, byte[] body) {
        log.info("=========================== Request ===========================");
        log.info("URI         : {}", request.getURI());
        log.info("Method      : {}", request.getMethod());
        log.info("Headers     : {}", request.getHeaders());

        if (isTextContentType(request.getHeaders().getContentType())) {
            log.info("Request Body: {}", new String(body, Charset.defaultCharset()));
        } else {
            log.info("Request Body: [Binary data]");
        }
        log.info("==============================================================");

    }

    protected void logResponse(ClientHttpResponse response) throws IOException {
        log.info("=========================== Response ==========================");
        log.info("Status code  : {}", response.getStatusCode());
        log.info("Status text  : {}", response.getStatusText());
        log.info("Headers      : {}", response.getHeaders());
        log.info("Response Body: {}", StreamUtils.copyToString(response.getBody(), Charset.defaultCharset()));
        log.info("==============================================================");
    }

    private Boolean isTextContentType(MediaType contentType) {
        return contentType != null &&
                (contentType.isCompatibleWith(MediaType.TEXT_PLAIN) ||
                        contentType.isCompatibleWith(MediaType.APPLICATION_JSON) ||
                        contentType.isCompatibleWith(MediaType.APPLICATION_XML));

    }
}




