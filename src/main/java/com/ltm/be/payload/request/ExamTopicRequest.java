package com.ltm.be.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamTopicRequest {
    @NotNull(message = "Exam id must not be null")
    private Long examId;
    @NotBlank(message = "Exam topic name must not be blank")
    private String name;
}
