package org.kodigo.subscriptions.enviroment;

import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.kodigo.subscriptions.utils.Constants.EMPTY_CHARACTER;


@Component
public class EnvironmentComponent {


    private String getEnvironmentVariable(String key) {
        return Optional.ofNullable(System.getenv(key)).orElse(EMPTY_CHARACTER);
    }
}
