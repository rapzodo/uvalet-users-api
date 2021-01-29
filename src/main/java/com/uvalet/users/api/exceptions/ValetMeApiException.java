package com.uvalet.users.api.exceptions;

import com.uvalet.users.api.enums.ErrorType;
import lombok.Data;

@Data
public class ValetMeApiException extends RuntimeException {
    private ErrorType error;
    private String rootCauseMessage;

    public ValetMeApiException(Throwable original) {
        addSuppressed(original);
        rootCauseMessage = original.getMessage();
    }
    public ValetMeApiException(ErrorType error, Throwable original) {
        initCause(original);
        this.error = error;
        this.rootCauseMessage = original.getMessage();
    }
}
