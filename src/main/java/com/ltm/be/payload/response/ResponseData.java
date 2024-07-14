package com.ltm.be.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class ResponseData <T>{
    private Integer status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    // for PUT, DELETE method
    public ResponseData(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    // for GET, POST,
    public ResponseData(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
