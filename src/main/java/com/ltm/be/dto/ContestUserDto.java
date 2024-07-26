package com.ltm.be.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContestUserDto extends AbstractDto<Long>{
    private UserDto user;
    private ContestDto contest;
    private boolean registered;
}
