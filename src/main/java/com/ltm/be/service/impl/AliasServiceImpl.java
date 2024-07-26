package com.ltm.be.service.impl;

import com.ltm.be.repository.AliasRepository;
import com.ltm.be.service.IAliasService;
import com.ltm.be.service.IRandomGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AliasServiceImpl implements IAliasService {
    private final IRandomGeneratorService randomGeneratorService;
    private final AliasRepository aliasRepository;
    @Override
    public String generateAliasCode(int size) {
        String aliasCode;
        do {
            aliasCode = randomGeneratorService.generate(size);
        } while(aliasRepository.existsByCodeAndActive(aliasCode, true));
        return aliasCode;
    }
}
