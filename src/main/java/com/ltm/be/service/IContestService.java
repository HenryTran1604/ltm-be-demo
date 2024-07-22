package com.ltm.be.service;

import com.ltm.be.dto.ContestDto;
import com.ltm.be.payload.request.ContestRequest;
import com.ltm.be.payload.request.RegistrationContestRequest;

import java.util.List;

public interface IContestService {
    void create(ContestRequest request);
    void update(Long id, ContestRequest request);
    List<ContestDto> getAllContest();
    ContestDto getContestById(Long id);
    void assignExercisesToUsers(Long id);
}
