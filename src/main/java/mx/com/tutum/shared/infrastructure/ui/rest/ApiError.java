package mx.com.tutum.shared.infrastructure.ui.rest;

import org.springframework.http.HttpStatus;

public final class ApiError {

    private final HttpStatus status;
    private final String error;
    private final String message;


    private ApiError(HttpStatus status, String message, String error) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public static ApiError create(HttpStatus status, String message, String error) {
        return new ApiError(status, message, error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}