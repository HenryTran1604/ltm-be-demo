package com.ltm.be.payload.request.webhook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamLogRequest extends Request {
    /*
    * examId: specify which exam
    * examUserId: for fast query, need to join user and exam
    * message: content of this request
    * */
    private Long examId;
    private Long examUserId;
    private String message;
    private int code;
}
