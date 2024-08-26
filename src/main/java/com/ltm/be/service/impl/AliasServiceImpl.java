package com.ltm.be.service.impl;

import com.ltm.be.converter.AliasConverter;
import com.ltm.be.dto.AliasDto;
import com.ltm.be.entity.AliasEntity;
import com.ltm.be.repository.AliasRepository;
import com.ltm.be.service.IAliasService;
import com.ltm.be.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class AliasServiceImpl extends BaseServiceImpl<AliasDto, AliasEntity, UUID> implements IAliasService {
    private final AliasRepository aliasRepository;
    private static final String allCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public AliasServiceImpl(AliasRepository aliasRepository, AliasConverter aliasConverter) {
        super(aliasRepository, aliasConverter);
        this.aliasRepository = aliasRepository;
    }
    public String generate(int size) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++) {
            sb.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }
        return sb.toString();
    }


    @Override
    public String generateAliasCode(int size) {
        String aliasCode;
        do {
            aliasCode = generate(size);
        } while(aliasRepository.existsByCodeAndActive(aliasCode, true));
        return aliasCode;
    }
}
