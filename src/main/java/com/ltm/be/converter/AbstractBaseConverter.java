package com.ltm.be.converter;

public abstract class AbstractBaseConverter <D, E>{
    public abstract D toDto(E entity);
}
