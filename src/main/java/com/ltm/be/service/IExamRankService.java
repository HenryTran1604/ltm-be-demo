package com.ltm.be.service;

import com.ltm.be.dto.ExamRankDto;

import java.util.List;

public interface IExamRankService {
    List<ExamRankDto> getAllByExamId(Long examId);
    ExamRankDto getByExamIdAndUserId(Long userId, Long examId);
}
