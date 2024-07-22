package com.ltm.be.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AliasRequest {
    private Long id;
    @NotBlank(message = "Alias name must be not empty")
    private String code;
}
