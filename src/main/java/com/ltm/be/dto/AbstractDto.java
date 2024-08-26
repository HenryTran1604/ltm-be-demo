package com.ltm.be.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Getter
@Setter
@SuperBuilder
public abstract class AbstractDto<T> {
    protected T id;
}
