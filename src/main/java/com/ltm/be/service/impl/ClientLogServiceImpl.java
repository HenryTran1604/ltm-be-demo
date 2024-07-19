package com.ltm.be.service.impl;

import com.ltm.be.converter.ClientLogConverter;
import com.ltm.be.dto.ClientLogDto;
import com.ltm.be.entity.ClientLogEntity;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.ClientLogRepository;
import com.ltm.be.service.IClientLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientLogServiceImpl implements IClientLogService {
    private final ClientLogRepository clientLogRepository;
    private final ClientLogConverter clientLogConverter;
    @Override
    public ClientLogDto saveLog(String message) {
        ClientLogEntity clientLog = ClientLogEntity.builder()
                .content(message)
                .build();
        ClientLogDto dto = clientLogConverter.toDto(clientLogRepository.save(clientLog));
        return  dto;
    }

    @Override
    public PageResponse<?> getAllClientLogs(int pageNo, int pageSize) {
        int page = 0;
        if(pageNo > 0) {
            page = pageNo - 1;
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ClientLogEntity> clientLogs = clientLogRepository.findAll(pageable);
        return PageResponse.builder()
                .page(pageNo)
                .size(pageSize)
                .totalPages(clientLogs.getTotalPages())
                .totalElements(clientLogs.getTotalElements())
                .items(clientLogs.stream().map(clientLogConverter::toDto).toList())
                .build();
    }

    @Override
    public void clearLogs() {
        clientLogRepository.deleteAll();
    }
}
