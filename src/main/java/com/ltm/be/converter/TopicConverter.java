package com.ltm.be.converter;

import com.ltm.be.dto.GroupDto;
import com.ltm.be.entity.GroupEntity;
import org.springframework.stereotype.Component;

@Component
public class TopicConverter extends AbstractBaseConverter<GroupDto, GroupEntity> {
    public GroupDto toDto(GroupEntity entity) {
        return GroupDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
