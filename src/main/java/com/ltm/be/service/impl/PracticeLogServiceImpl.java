package com.ltm.be.service.impl;

import com.ltm.be.converter.PracticeLogConverter;
import com.ltm.be.dto.PracticeLogDto;
import com.ltm.be.entity.PracticeLogEntity;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.request.webhook.PracticeLogRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.PracticeLogRepository;
import com.ltm.be.repository.UserRepository;
import com.ltm.be.service.IPracticeLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PracticeLogServiceImpl implements IPracticeLogService {
    private final PracticeLogRepository practiceLogRepository;
    private final PracticeLogConverter practiceLogConverter;
    private final UserRepository userRepository;

    @Override
    public PracticeLogDto savePracticeLog(PracticeLogRequest request) {
        PracticeLogEntity log = PracticeLogEntity.builder()
                .user(userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new ResourceNotFoundException("Username not found")))
                .content(request.getMessage())
                .build();
        return practiceLogConverter.toDto(practiceLogRepository.save(log));
    }

    @Override
    public PageResponse<?> getPracticeLogByUser(Long userId, int pageNo, int pageSize) {
        int page = 0;
        if(pageNo > 0) {
            page = pageNo - 1;
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<PracticeLogEntity> clientLogs = practiceLogRepository.findAllByUserId(userId, pageable);
        return PageResponse.builder()
                .page(pageNo)
                .size(pageSize)
                .totalPages(clientLogs.getTotalPages())
                .totalElements(clientLogs.getTotalElements())
                .items(clientLogs.stream().map(practiceLogConverter::toDto).toList())
                .build();
    }

    @Override
    public void clearLogs() {

    }
}
