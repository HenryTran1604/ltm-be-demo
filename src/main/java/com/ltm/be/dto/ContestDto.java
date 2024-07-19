package com.ltm.be.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContestDto extends AbstractDto<Long>{
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String title;
}
