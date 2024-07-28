package com.ltm.be.payload.request.webhook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    /*
    * username: for spring to specify send to which user
    * */
    protected String username;
    protected String ip;
    protected String alias;
}
