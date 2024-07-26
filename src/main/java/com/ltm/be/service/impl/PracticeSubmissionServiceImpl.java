package com.ltm.be.service.impl;

import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.service.IPracticeSubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PracticeSubmissionServiceImpl implements IPracticeSubmissionService {
    @Override
    public PageResponse<?> getAllByUserId(Long userId, int pageNo, int pageSize) {
        return null;
    }

    @Override
    public PageResponse<?> getAllByUserIdAndContestId(Long id, int pageNo, int pageSize) {
        return null;
    }
}
