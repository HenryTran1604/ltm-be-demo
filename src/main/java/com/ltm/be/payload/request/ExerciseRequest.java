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
public class ExerciseRequest {
    @NotBlank(message = "Exercise name must not be blank")
    private String name;
    @NotBlank(message = "Exercise content must not be blank")
    private String content;
    @NotBlank(message = "Exercise alias must not be blank")
    private String alias;
    @NotBlank(message = "Exercise must be in a topic")
    private Integer topicId;
}
