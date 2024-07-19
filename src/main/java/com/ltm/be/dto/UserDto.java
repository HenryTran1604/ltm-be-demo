package com.ltm.be.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends AbstractDto<Long>{
    private String username;
    private String ip;
    private String role;
}
