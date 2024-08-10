package com.ltm.be.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ExamUserDto extends AbstractDto<Long>{
    private UserDto user;
    private ExamDto exam;
    private boolean registered;
}
