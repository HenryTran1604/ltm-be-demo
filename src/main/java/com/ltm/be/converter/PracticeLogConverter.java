package com.ltm.be.converter;

import com.ltm.be.dto.PracticeLogDto;
import com.ltm.be.entity.PracticeLogEntity;
import org.springframework.stereotype.Component;

import static com.ltm.be.util.Constants.*;
import static com.ltm.be.util.Constants.FORBIDDEN;

@Component
public class PracticeLogConverter {
    private String getStatusFromCode(int code) {
        if (code == CONNECT_SUCCESS) {
            return "CONNECT SUCCESSFULLY";
        }
        if (code == ACCEPTED) {
            return "ACCEPTED";
        }
        if (code == WRONG_ANSWER) {
            return "WRONG ANSWER";
        }
        if (code == INVALID_FORMAT_INPUT) {
            return "INVALID FORMAT INPUT";
        }
        if (code == TIME_OUT) {
            return "TIME OUT";
        }
        if (code == REQUEST_WRONG_EXERCISE) {
            return "WRONG EXERCISE";
        }
        if (code == MALFORMED_REQUEST_CODE) {
            return "MALFORMED REQUEST CODE";
        }
        if (code == FORBIDDEN) {
            return "FORBIDDEN";
        }
        return "UNKNOWN STATUS";
    }
    public PracticeLogDto toDto(PracticeLogEntity entity) {
        PracticeLogDto dto = new PracticeLogDto();
        dto.setId(entity.getId());
        dto.setMessage(entity.getMessage());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUserId(entity.getUser().getId());
        dto.setCode(getStatusFromCode(entity.getCode()));
        dto.setAlias(entity.getAlias());
        return dto;
    }
}
