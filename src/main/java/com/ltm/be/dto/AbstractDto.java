package com.ltm.be.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public abstract class AbstractDto<T> {
    protected T id;
    protected LocalDateTime createdAt;
}
