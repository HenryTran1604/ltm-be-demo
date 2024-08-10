package com.ltm.be.payload.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamRegistrationRequest {
    @NotNull(message = "exam id must be not null")
    private Long examId;
    @NotNull(message = "user id must be not null")
    private Long userId;
}
