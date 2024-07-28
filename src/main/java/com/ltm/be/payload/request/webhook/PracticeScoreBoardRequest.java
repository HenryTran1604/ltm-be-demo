package com.ltm.be.payload.request.webhook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PracticeScoreBoardRequest extends Request {
    private Long userId;
}
