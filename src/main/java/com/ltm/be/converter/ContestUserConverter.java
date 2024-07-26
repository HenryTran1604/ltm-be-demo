package com.ltm.be.converter;

import com.ltm.be.dto.ContestUserDto;
import com.ltm.be.entity.ContestUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContestUserConverter {
    private final ContestConverter contestConverter;
    private final UserConverter userConverter;

    public ContestUserDto toDto(ContestUserEntity entity) {
        ContestUserDto dto = new ContestUserDto();
        dto.setUser(userConverter.toDto(entity.getUser()));
        dto.setContest(contestConverter.toDto(entity.getContest()));
        return dto;
    }
}
