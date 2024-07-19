package com.ltm.be.exception;


public class UsernameAndIpAlreadyExistException extends RuntimeException {
    public UsernameAndIpAlreadyExistException(String message) {
        super(message);
    }

    public UsernameAndIpAlreadyExistException(Throwable throwable) {
        super(throwable);
    }
}
