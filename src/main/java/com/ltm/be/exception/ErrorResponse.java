package com.ltm.be.exception;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@Builder
public class ErrorResponse {
    private Date timestamp;
    private Integer status;
    private String path;
    private String error;
    private String message;
}
