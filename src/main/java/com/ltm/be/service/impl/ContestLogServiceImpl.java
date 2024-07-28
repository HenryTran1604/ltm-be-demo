package com.ltm.be.service.impl;

import com.ltm.be.converter.ContestLogConverter;
import com.ltm.be.dto.ContestLogDto;
import com.ltm.be.entity.ContestLogEntity;
import com.ltm.be.payload.request.webhook.ContestLogRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.ContestLogRepository;
import com.ltm.be.repository.ContestRepository;
import com.ltm.be.repository.ContestUserRepository;
import com.ltm.be.service.IContestLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContestLogServiceImpl implements IContestLogService {
    private final ContestLogRepository contestLogRepository;
    private final ContestLogConverter contestLogConverter;
    private final ContestRepository contestRepository;
    private final ContestUserRepository contestUserRepository;
    @Override
    public ContestLogDto saveContestLog(ContestLogRequest request) {
        ContestLogEntity clientLog = ContestLogEntity.builder()
                .contest(contestRepository.getReferenceById(request.getContestId()))
                .contestUser(contestUserRepository.getReferenceById(request.getContestUserId()))
                .content(request.getMessage())
                .build();
        return contestLogConverter.toDto(contestLogRepository.save(clientLog));
    }

    @Override
    public PageResponse<?> getContestLogByUser(Long contestId, Long userId, int pageNo, int pageSize) {
        int page = 0;
        if(pageNo > 0) {
            page = pageNo - 1;
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ContestLogEntity> clientLogs = contestLogRepository.findAllByContestId(contestId, pageable);
        return PageResponse.builder()
                .page(pageNo)
                .size(pageSize)
                .totalPages(clientLogs.getTotalPages())
                .totalElements(clientLogs.getTotalElements())
                .items(clientLogs.stream().map(contestLogConverter::toDto).toList())
                .build();
    }

    @Override
    public void clearLogs() {
        contestLogRepository.deleteAll();
    }
}
