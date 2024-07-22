package com.ltm.be.payload.request;

import com.ltm.be.dto.AliasDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseRequest {
    @NotBlank(message = "Exercise name must not be blank")
    private String name;
    @NotBlank(message = "Exercise content must not be blank")
    private String content;
    @NotEmpty(message = "Exercise aliases must not be empty")
    private List<AliasRequest> aliases;
    @NotBlank(message = "Exercise must be in a topic")
    private Integer topicId;
}
