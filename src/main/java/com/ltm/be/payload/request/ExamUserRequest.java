package com.ltm.be.payload.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamUserRequest {
    @NotNull(message = "exam must not be null")
    private Long examId;
    @NotEmpty(message = "Users must not be empty")
    private List<Long> userIds;
}
