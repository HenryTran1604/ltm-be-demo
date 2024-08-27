package com.ltm.be.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class ExamUserDto extends AbstractDto<UUID> {
    private UserDto user;
    private ExamDto exam;
    private boolean registered;
}
