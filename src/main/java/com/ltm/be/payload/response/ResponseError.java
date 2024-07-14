package com.ltm.be.payload.response;


public class ResponseError<T> extends ResponseData<T> {
    public ResponseError(Integer status, String message) {
        super(status, message);
    }
}
