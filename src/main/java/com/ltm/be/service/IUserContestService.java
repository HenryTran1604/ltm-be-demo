package com.ltm.be.service;

import com.ltm.be.payload.request.UserContestRequest;

public interface IUserContestService {
    void addUsersToContest(UserContestRequest request);
}
