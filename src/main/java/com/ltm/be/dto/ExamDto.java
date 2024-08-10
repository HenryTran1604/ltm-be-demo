package com.ltm.be.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class ExamDto extends AbstractDto<Long>{
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String title;
}
