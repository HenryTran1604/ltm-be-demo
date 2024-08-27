package com.ltm.be.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class UserDto extends AbstractDto<UUID> {
    private String userName;
    private String ipAddress;
    private String role;
}
