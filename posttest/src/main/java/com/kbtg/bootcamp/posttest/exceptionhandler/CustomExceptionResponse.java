package com.kbtg.bootcamp.posttest.exceptionhandler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomExceptionResponse {
    private final String errorMessage;

    public CustomExceptionResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
