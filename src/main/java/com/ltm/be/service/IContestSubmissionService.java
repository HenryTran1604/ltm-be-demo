package com.ltm.be.service;

import com.ltm.be.payload.response.PageResponse;

public interface IContestSubmissionService {
    PageResponse<?> getAllByUserId(Long userId, int pageNo, int pageSize);

    PageResponse<?> getAllByUserIdAndContestId(Long id, int pageNo, int pageSize);
}
