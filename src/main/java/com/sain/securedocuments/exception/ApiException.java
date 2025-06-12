package com.sain.securedocuments.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
    public ApiException() {
        super("An Error occured");
    }

}
