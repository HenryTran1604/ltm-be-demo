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
public class TopicRequest {
    @NotBlank(message = "Topic name must be not blank")
    private String name;
    @NotBlank(message = "Topic content must be not blank")
    private String content;
}
