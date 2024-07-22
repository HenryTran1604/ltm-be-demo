package com.ltm.be.service;

import com.ltm.be.payload.request.RegistrationContestRequest;
import com.ltm.be.payload.request.UserContestRequest;
import com.ltm.be.payload.response.PageResponse;

public interface IUserContestService {
    void addUsersToContest(UserContestRequest request);

    void registerContest(RegistrationContestRequest request);

    PageResponse<?> getAllContestWithRegistration(Long userId, int pageNo, int pageSize);

    PageResponse<?> getAllRegisteredUserByContestId(Long contestId, int pageNo, int pageSize);
}
