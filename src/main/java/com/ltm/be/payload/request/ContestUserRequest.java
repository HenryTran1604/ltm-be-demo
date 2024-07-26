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
public class ContestUserRequest {
    @NotNull(message = "Contest must not be null")
    private Long contestId;
    @NotEmpty(message = "Users must not be empty")
    private List<Long> userIds;
}
