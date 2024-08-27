package com.ltm.be.payload.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExamUserRequest {
    @NotNull(message = "exam must not be null")
    private UUID examId;
    @NotEmpty(message = "Users must not be empty")
    private List<UUID> userIds;
}
