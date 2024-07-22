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
public class RegistrationContestRequest {
    @NotNull(message = "contest id must be not null")
    private Long contestId;
    @NotNull(message = "user id must be not null")
    private Long userId;
}
