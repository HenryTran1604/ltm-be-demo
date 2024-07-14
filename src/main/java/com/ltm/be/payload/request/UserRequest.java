package com.ltm.be.payload.request;

import com.ltm.be.util.IP;
import com.ltm.be.util.StudentCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @StudentCode
    private String studentCode;
    @IP
    private String ip;
}
