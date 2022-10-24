package com.example.proxy.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends Exception {

    private static final long serialVersionUID = -9079454849611061074L;

    public ResourceNotFound() {
        super();
    }

    public ResourceNotFound(final String message) {
        super(message);
    }

}
