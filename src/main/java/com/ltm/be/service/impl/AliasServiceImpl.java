package com.ltm.be.service.impl;

import com.ltm.be.converter.AliasConverter;
import com.ltm.be.dto.AliasDto;
import com.ltm.be.entity.AliasEntity;
import com.ltm.be.repository.AliasRepository;
import com.ltm.be.service.IAliasService;
import com.ltm.be.service.IRandomGeneratorService;
import com.ltm.be.service.base.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class AliasServiceImpl extends BaseServiceImpl<AliasDto, AliasEntity, Long> implements IAliasService {
    private final AliasRepository aliasRepository;
    private final IRandomGeneratorService randomGeneratorService;

    public AliasServiceImpl(IRandomGeneratorService randomGeneratorService,
                            AliasRepository aliasRepository,
                            AliasConverter aliasConverter) {
        super(aliasRepository, aliasConverter);
        this.randomGeneratorService = randomGeneratorService;
        this.aliasRepository = aliasRepository;
    }

    @Override
    public String generateAliasCode(int size) {
        String aliasCode;
        do {
            aliasCode = randomGeneratorService.generate(size);
        } while(aliasRepository.existsByCodeAndActive(aliasCode, true));
        return aliasCode;
    }
}
