package com.ltm.be.service;

import com.ltm.be.payload.request.ContestRegistrationRequest;
import com.ltm.be.payload.request.ContestUserRequest;
import com.ltm.be.payload.response.PageResponse;

public interface IContestUserService {
    void addUsersToContest(ContestUserRequest request);

    void registerContest(ContestRegistrationRequest request);

    PageResponse<?> getAllContestWithRegistration(Long userId, int pageNo, int pageSize);

    PageResponse<?> getAllRegisteredUserByContestId(Long contestId, int pageNo, int pageSize);
}
