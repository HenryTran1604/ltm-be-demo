package com.ltm.be.payload.request;

import com.ltm.be.util.IP;
import com.ltm.be.util.StudentCode;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @StudentCode
    private String studentCode;
    @IP
    private String ip;
    @NotBlank(message = "Password must be not blank")
    private String password;
}
