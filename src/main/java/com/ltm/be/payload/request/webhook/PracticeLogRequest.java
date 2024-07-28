package com.ltm.be.payload.request.webhook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PracticeLogRequest extends Request {
    private String message;
    private int code;
}
