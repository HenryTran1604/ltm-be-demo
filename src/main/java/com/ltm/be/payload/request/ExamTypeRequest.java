package com.ltm.be.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ExamTypeRequest {
    @NotBlank(message = "Exam type must not be blank")
    private String name;
}
