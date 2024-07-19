package com.ltm.be.exception;


public class LoginFailException extends RuntimeException {
    public LoginFailException(String message) {
        super(message);
    }

    public LoginFailException(Throwable throwable) {
        super(throwable);
    }
}
