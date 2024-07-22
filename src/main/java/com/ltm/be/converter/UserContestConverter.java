package com.ltm.be.converter;

import com.ltm.be.dto.UserContestDto;
import com.ltm.be.entity.UserContestEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserContestConverter {
    private final ContestConverter contestConverter;
    private final UserConverter userConverter;

    public UserContestDto toDto(UserContestEntity entity) {
        UserContestDto dto = new UserContestDto();
        dto.setUser(userConverter.toDto(entity.getUser()));
        dto.setContest(contestConverter.toDto(entity.getContest()));
        return dto;
    }
}
