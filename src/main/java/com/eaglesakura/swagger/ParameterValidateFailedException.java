package com.eaglesakura.swagger;

import java.io.IOException;

public class ParameterValidateFailedException extends IOException {
    public ParameterValidateFailedException() {
    }

    public ParameterValidateFailedException(String message) {
        super(message);
    }

    public ParameterValidateFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParameterValidateFailedException(Throwable cause) {
        super(cause);
    }
}
