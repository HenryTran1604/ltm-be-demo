package com.ltm.be.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class UserDto extends AbstractDto<Long>{
    private String username;
    private String ip;
    private String role;
}
