package com.ltm.be.exception;

import org.apache.coyote.BadRequestException;

public class UsernameAndIpAlreadyExistException extends BadRequestException {
    public UsernameAndIpAlreadyExistException(String message) {
        super(message);
    }

    public UsernameAndIpAlreadyExistException(Throwable throwable) {
        super(throwable);
    }
}
