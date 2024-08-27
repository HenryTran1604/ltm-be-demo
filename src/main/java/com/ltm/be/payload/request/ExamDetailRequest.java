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
public class ExamDetailRequest {
    @NotNull(message = "exam id must not be null")
    private UUID examId;
    @NotEmpty(message = "exercises must not be empty")
    private List<UUID> exerciseIds;
}
