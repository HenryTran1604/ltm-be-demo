package com.ltm.be.payload.request.webhook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContestLogRequest extends Request {
    /*
    * contestId: specify which contest
    * contestUserId: for fast query, need to join user and contest
    * message: content of this request
    * */
    private Long contestId;
    private Long contestUserId;
    private String message;
    private int code;
}
