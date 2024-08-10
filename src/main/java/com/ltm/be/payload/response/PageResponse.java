package com.ltm.be.payload.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageResponse<D> {
    private int page;
    private int size;
    private long totalPages;
    private long totalElements;
    private D items;
}
