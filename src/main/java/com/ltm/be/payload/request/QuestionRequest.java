package com.ltm.be.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest {
    @NotBlank(message = "Question name must not be blank")
    private String name;
    @NotBlank(message = "Question content must not be blank")
    private String content;
    @NotBlank(message = "Question must be in a group")
    private UUID groupId;

    private String code;

    private Integer level;

    private String subGroup;

    private Integer type; // ???

    private Integer status;

    private String solution;

    private String hint;

}
